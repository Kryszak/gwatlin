package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.http.exception.RetrieveError
import com.kryszak.gwatlin.http.config.HttpConfig
import com.kryszak.gwatlin.http.exception.ErrorResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

internal open class BaseHttpClient {

    companion object {
        val log: Logger = getLogger(BaseHttpClient::class.java.simpleName)
    }

    protected val logMessage = "Requested url: %s"

    protected val baseUrl: String

    private val gson = Gson()

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected inline fun <reified T : Any> getRequest(uri: String): T {
        val (_, response, result) = "$baseUrl/$uri"
                .httpGet()
                .also { log.info(logMessage.format(it.url)) }
                .responseObject<T>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
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
