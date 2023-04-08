package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.kryszak.gwatlin.http.exception.RetrieveError
import com.kryszak.gwatlin.http.exception.ErrorResponse

internal open class AuthenticatedHttpClient(
    val apiKey: String,
    schemaVersion: String? = null,
    defaultLanguage: String = "en"
) : BaseHttpClient(schemaVersion, defaultLanguage) {

    protected inline fun <reified T : Any> getRequestAuth(uri: String, language: String? = null) =
        getRequest<T>(uri, language) {
            it.authentication().bearer(apiKey)
        }
}
