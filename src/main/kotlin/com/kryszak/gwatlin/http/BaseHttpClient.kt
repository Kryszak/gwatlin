package com.kryszak.gwatlin.http

import com.github.kittinunf.result.Result
import com.kryszak.gwatlin.http.config.HttpConfig

open class BaseHttpClient {

    protected val baseUrl: String

    private val httpConfig: HttpConfig = HttpConfig()

    init {
        baseUrl = httpConfig.baseUrl
    }

    protected fun <T: Any> processResult(result: Result<T, Exception>): T {
        when (result) {
            is Result.Success -> {
                return result.get()
            }
            is Result.Failure -> {
                throw result.getException()
            }
        }
    }
}
