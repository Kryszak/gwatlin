package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import com.kryszak.gwatlin.http.config.HttpConfig
import com.kryszak.gwatlin.http.exception.ErrorResponse
import com.kryszak.gwatlin.http.exception.RetrieveError
import com.kryszak.gwatlin.serializers.DimensionsSerializer
import com.kryszak.gwatlin.serializers.PairSerializer
import com.kryszak.gwatlin.serializers.RectangleSerializer
import mu.KotlinLogging

internal open class BaseHttpClient(
    private val schemaVersion: String? = null,
    private val defaultLanguage: ApiLanguage? = null
) {

    private val log = KotlinLogging.logger {}

    protected val logMessage = "Requested url: %s"

    protected val baseUrl: String

    protected val gson = GsonBuilder()
        .registerTypeAdapter(Pair::class.java, PairSerializer())
        .registerTypeAdapter(Dimensions::class.java, DimensionsSerializer())
        .registerTypeAdapter(Rectangle::class.java, RectangleSerializer())
        .create()

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
                .responseObject<T>(gson)

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    protected fun addDefaultHeaders(request: Request, language: ApiLanguage?) {
        schemaVersion?.let { request.appendHeader("X-Schema-Version" to it) }

        // Language priority list:
        // 1. Language specified for this request, if any
        // 2. Set default language for this client, if any
        // If one of the mentioned languages is not null, the Accept-Language header gets set
        arrayOf(language, defaultLanguage)
            .firstOrNull { it != null }
            ?.let { request.appendHeader(Headers.ACCEPT_LANGUAGE to it.apiString) }
    }


    protected fun encodeParam(param: String) = param.replace(" ", "%20")

    protected fun <T : Any, E : Any> processResult(result: Result<T, Exception>, errorResponse: ErrorResponse<E>): T {
        when (result) {
            is Result.Success -> return result.get()
            is Result.Failure -> {
                log.error("Request failed! ${result.getException().message}")
                try {
                    val error = gson.fromJson(String(errorResponse.response.data), errorResponse.responseType)
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
