package io.github.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.http.config.HttpConfig
import io.github.kryszak.gwatlin.http.exception.ErrorResponse
import io.github.kryszak.gwatlin.http.exception.RetrieveError
import io.github.kryszak.gwatlin.http.serializers.JsonConfigurer.json
import kotlinx.serialization.serializer
import mu.KotlinLogging

internal open class BaseHttpClient(
    private val schemaVersion: String? = null
) {

    private val log = KotlinLogging.logger {}

    private val logMessage = "Requested url: %s"

    private val baseUrl: String

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected inline fun <reified T : Any> getRequest(
        uri: String,
        language: io.github.kryszak.gwatlin.api.ApiLanguage? = null,
        configBlock: Request.() -> Unit = {}
    ): T {
        val (_, response, result) = "$baseUrl$uri"
                .httpGet()
                .also { addDefaultHeaders(it, language) }
                .also { log.info(logMessage.format(it.url)) }
                .also { configBlock(it) }
                // The Fuel extension package doesn't acknowledge default serializers.
                // The default serializers need to be passed manually, sadly
                .responseObject<T>(json.serializersModule.serializer(), json)

        return processResult(result, ErrorResponse(response, RetrieveError.serializer()))
    }

    protected fun encodeParam(param: String) = param.replace(" ", "%20")

    private fun addDefaultHeaders(request: Request, language: io.github.kryszak.gwatlin.api.ApiLanguage?) {
        schemaVersion?.let { request.appendHeader("X-Schema-Version" to it) }
        language?.let { request.appendHeader(Headers.ACCEPT_LANGUAGE to it.apiString) }
    }

    private fun <T : Any, E : Any> processResult(result: Result<T, Exception>, errorResponse: ErrorResponse<E>): T {
        when (result) {
            is Result.Success -> return result.get()
            is Result.Failure -> {
                log.error("Request failed! ${result.getException().message}")
                try {
                    val error = decodeErrorResponse(errorResponse)
                    log.error("Error: $error")
                    throw ApiRequestException(error)
                } catch (e: IllegalStateException) {
                    log.warn("Failed to deserialize error response", e)
                    throw ApiRequestException("Unknown error")
                }
            }
        }
    }

    private fun <E : Any> decodeErrorResponse(errorResponse: ErrorResponse<E>): String {
        try {
            val error = json.decodeFromString(
                errorResponse.deserializationStrategy,
                String(errorResponse.response.data)
            )
            return error.toString()
        } catch (exception: Exception) {
            return String(errorResponse.response.data)
        }
    }
}
