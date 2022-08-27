package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.http.exception.RetrieveError
import com.kryszak.gwatlin.http.exception.ErrorResponse

internal open class AuthenticatedHttpClient(
    val apiKey: String,
    schemaVersion: String? = null
) : BaseHttpClient(schemaVersion) {

    protected inline fun <reified T : Any> getRequestAuth(uri: String): T {
        val (_, response, result) = "$baseUrl/$uri"
                .httpGet()
                .also { addDefaultHeaders(it) }
                .also { log.info(logMessage.format(it.url)) }
                .authentication()
                .bearer(apiKey)
                .responseObject<T>()

        return processResult(result, ErrorResponse(response, RetrieveError::class.java))
    }
}
