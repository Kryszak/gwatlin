package io.github.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.config.HttpConfig
import io.github.kryszak.gwatlin.http.exception.RetrieveError
import io.github.kryszak.gwatlin.http.serializers.JsonConfigurer.json
import kotlinx.serialization.SerializationException
import kotlinx.serialization.serializer
import mu.KotlinLogging

internal abstract class BaseHttpClient(
    private val schemaVersion: String? = null,
) {

    private val log = KotlinLogging.logger {}

    private val logMessage = "Requested url: %s"

    private val httpConfig: HttpConfig = HttpConfig()

    protected inline fun <reified T : Any> getRequest(
        uri: String,
        language: ApiLanguage? = null,
        configureRequest: Request.() -> Unit = {},
    ): T {
        val (_, _, result) = doRequest<T>(uri, language, configureRequest)
        return processResult(result)
    }

    protected inline fun <reified T : Any> getPagedRequest(
        uri: String,
        language: ApiLanguage? = null,
        configureRequest: Request.() -> Unit = {},
    ): PagedResponse<T> {
        val (_, response, result) = doRequest<T>(uri, language, configureRequest)
        val objects = processResult(result)
        return PagedResponse(
            objects,
            response.header("X-Page-Size").first().toInt(),
            response.header("X-Page-Total").first().toInt(),
            response.header("X-Result-Count").first().toInt(),
            response.header("X-Result-Total").first().toInt()
        )
    }

    private inline fun <reified T : Any> doRequest(
        uri: String,
        language: ApiLanguage?,
        configureRequest: Request.() -> Unit,
    ) = "${httpConfig.baseUrl}${uri}"
        .httpGet()
        .timeout(httpConfig.connectTimeout)
        .timeoutRead(httpConfig.readTimeout)
        .also { addDefaultHeaders(it, language) }
        .also { log.info(logMessage.format(it.url)) }
        .also(configureRequest)
        // The Fuel extension package doesn't acknowledge default serializers.
        // The default serializers need to be passed manually, sadly
        .responseObject<T>(json.serializersModule.serializer(), json)

    protected fun encodeParam(param: String) = param.replace(" ", "%20")

    private fun addDefaultHeaders(request: Request, language: ApiLanguage?) {
        schemaVersion?.let { request.appendHeader("X-Schema-Version" to it) }
        language?.let { request.appendHeader(Headers.ACCEPT_LANGUAGE to it.apiString) }
    }

    private fun <T : Any> processResult(result: Result<T, FuelError>): T {
        return when (result) {
            is Result.Success -> result.get()
            is Result.Failure -> handleErrorResponse(result)
        }
    }

    private fun handleErrorResponse(result: Result.Failure<FuelError>): Nothing {
        log.error("Request failed! ${result.getException().message}")
        throw ApiRequestException(decodeErrorResponse(result))
    }

    private fun decodeErrorResponse(failure: Result.Failure<FuelError>): String {
        if (failure.getException().response.body().isConsumed()) {
            return failure.getException().message ?: "Unknown error."
        }

        try {
            val error = json.decodeFromString(
                RetrieveError.serializer(),
                String(failure.getException().response.data)
            )
            return error.toString()
        } catch (e: SerializationException) {
            return failure.getException().message ?: "Unknown error."
        }
    }
}
