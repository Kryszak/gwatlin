package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.GsonBuilder
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import com.kryszak.gwatlin.http.exception.RetrieveError
import com.kryszak.gwatlin.http.config.HttpConfig
import com.kryszak.gwatlin.http.exception.ErrorResponse
import com.kryszak.gwatlin.serializers.RectangleSerializer
import com.kryszak.gwatlin.serializers.DimensionsSerializer
import com.kryszak.gwatlin.serializers.PairSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

internal open class BaseHttpClient(
    protected val schemaVersion: String? = null
) {

    companion object {
        val log: Logger = getLogger(BaseHttpClient::class.java.simpleName)
    }

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

    protected inline fun <reified T : Any> getRequest(uri: String): T {
        val (_, response, result) = "$baseUrl/$uri"
                .httpGet()
                .also { addDefaultHeaders(it) }
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<T>(gson)

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }

    protected fun addDefaultHeaders(request: Request) {
        schemaVersion?.let { request.appendHeader("X-Schema-Version" to schemaVersion) }
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
