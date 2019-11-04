package com.kryszak.gwatlin.clients.token

import com.kryszak.gwatlin.api.token.model.Token
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class TokenClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val tokenEndpoint = "tokeninfo"

    fun getTokenInfo(): Token {
        return getRequestAuth(tokenEndpoint)
    }
}
