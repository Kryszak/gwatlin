package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.serialization.responseObject
import com.github.kittinunf.result.Result
import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.http.config.HttpConfig
import com.kryszak.gwatlin.http.exception.ErrorResponse
import com.kryszak.gwatlin.http.exception.RetrieveError
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import com.kryszak.gwatlin.http.serializers.DimensionsSerializer
import com.kryszak.gwatlin.http.serializers.PairSerializer
import com.kryszak.gwatlin.http.serializers.RectangleSerializer
import mu.KotlinLogging

internal open class BaseHttpClient(
    private val schemaVersion: String? = null
) {

    private val log = KotlinLogging.logger {}

    protected val logMessage = "Requested url: %s"

    protected val baseUrl: String

    protected val json = Json {
        isLenient = true
    }

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected inline fun <reified T : Any> getRequest(uri: String, language: ApiLanguage? = null, configBlock: Request.() -> Unit = {}): T {
        val (_, response, result) = "$baseUrl/$uri"
                .httpGet()
                .also { addDefaultHeaders(it, language) }
                .also { log.info(logMessage.format(it.url)) }
                .also { configBlock(it) }
                // The Fuel extension package doesn't acknowledge default serializers.
                // The default serializers need to be passed manually, sadly
                .responseObject<T>(json.serializersModule.serializer(), json)

        return processResult(result, ErrorResponse(response, RetrieveError.serializer()))
    }

    protected fun addDefaultHeaders(request: Request, language: ApiLanguage?) {
        schemaVersion?.let { request.appendHeader("X-Schema-Version" to it) }
        language?.let { request.appendHeader(Headers.ACCEPT_LANGUAGE to it.apiString) }
    }


    protected fun encodeParam(param: String) = param.replace(" ", "%20")

    protected fun <T : Any, E : Any> processResult(result: Result<T, Exception>, errorResponse: ErrorResponse<E>): T {
        when (result) {
            is Result.Success -> return result.get()
            is Result.Failure -> {
                log.error("Request failed! ${result.getException().message}")
                try {
                    val error = json.decodeFromString(errorResponse.deserializationStrategy, String(errorResponse.response.data))
                    log.error("Error: $error")
                    throw ApiRequestException(error.toString())
                } catch (e: IllegalStateException) {
                    log.warn("Failed to deserialize error response", e)
                    throw ApiRequestException("Unknown error")
                }
            }
        }
    }
}
