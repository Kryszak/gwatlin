package com.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.extensions.authentication

internal open class AuthenticatedHttpClient(
    val apiKey: String,
    schemaVersion: String? = null,
    defaultLanguage: String = "en"
) : BaseHttpClient(schemaVersion, defaultLanguage) {

    protected inline fun <reified T : Any> getRequestAuth(uri: String, language: String? = null, configBlock: Request.() -> Unit = {}) =
        getRequest<T>(uri, language) {
            authentication().bearer(apiKey)
            configBlock(this)
        }
}
