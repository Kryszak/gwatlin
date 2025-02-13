package io.github.kryszak.gwatlin.clients.miscellaneous

import io.github.kryszak.gwatlin.api.miscellaneous.model.Token
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class TokenClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val tokenEndpoint = "/tokeninfo"

    fun getTokenInfo(): Token {
        return getRequestAuth(tokenEndpoint)
    }
}
