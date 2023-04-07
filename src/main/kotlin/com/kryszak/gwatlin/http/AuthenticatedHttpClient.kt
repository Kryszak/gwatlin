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

    protected inline fun <reified T : Any> getRequestAuth(uri: String) =
        getRequest<T>(uri) {
            it.authentication().bearer(apiKey)
        }
}
