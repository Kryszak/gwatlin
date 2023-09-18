package io.github.kryszak.gwatlin.clients.token

import io.github.kryszak.gwatlin.api.token.model.Token
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class TokenClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val tokenEndpoint = "tokeninfo"

    fun getTokenInfo(): Token {
        return getRequestAuth(tokenEndpoint)
    }
}
