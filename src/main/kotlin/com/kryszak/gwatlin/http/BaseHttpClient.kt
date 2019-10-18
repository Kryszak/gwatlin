package com.kryszak.gwatlin.http

import com.github.kittinunf.result.Result
import com.kryszak.gwatlin.api.model.achievement.ApiRequestException
import com.kryszak.gwatlin.http.config.HttpConfig
import java.util.logging.Logger

internal open class BaseHttpClient {

    companion object {
        val log: Logger = Logger.getLogger(BaseHttpClient::class.java.simpleName)
    }

    protected val baseUrl: String

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected fun <T : Any> processResult(result: Result<T, Exception>): T {
        when (result) {
            is Result.Success -> {
                return result.get()
            }
            is Result.Failure -> {
                val message = result.getException().message
                log.severe(message)
                throw ApiRequestException(message)
            }
        }
    }
}
