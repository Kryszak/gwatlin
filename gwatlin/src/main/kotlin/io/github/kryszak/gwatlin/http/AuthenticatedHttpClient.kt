package io.github.kryszak.gwatlin.http

import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.extensions.authentication
import io.github.kryszak.gwatlin.api.ApiLanguage

internal abstract class AuthenticatedHttpClient(
    val apiKey: String,
    schemaVersion: String? = null,
) : BaseHttpClient(schemaVersion) {

    protected inline fun <reified T : Any> getRequestAuth(
        uri: String,
        language: ApiLanguage? = null,
        configureRequest: Request.() -> Unit = {}
    ) =
        getRequest<T>(uri, language) {
            authentication().bearer(apiKey)
            configureRequest(this)
        }

    protected inline fun <reified T : Any> getPagedRequestAuth(
        uri: String,
        language: ApiLanguage? = null,
        configureRequest: Request.() -> Unit = {},
    ) =
        getPagedRequest<T>(uri, language) {
            authentication().bearer(apiKey)
            configureRequest(this)
        }
}
