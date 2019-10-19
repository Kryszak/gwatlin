package com.kryszak.gwatlin.http

import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException
import com.kryszak.gwatlin.http.config.HttpConfig
import com.kryszak.gwatlin.http.exception.ErrorResponse
import java.util.logging.Logger

internal open class BaseHttpClient {

    companion object {
        val log: Logger = Logger.getLogger(BaseHttpClient::class.java.simpleName)
    }

    protected val baseUrl: String

    private val gson = Gson()

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected fun <T : Any, E: Any> processResult(result: Result<T, Exception>, errorResponse: ErrorResponse<E>): T {
        when (result) {
            is Result.Success -> {
                return result.get()
            }
            is Result.Failure -> {
                val error = gson.fromJson(String(errorResponse.response.data), errorResponse.responseType)
                log.severe(result.getException().message)
                log.severe("Error: $error")
                throw ApiRequestException(error.toString())
            }
        }
    }
}
