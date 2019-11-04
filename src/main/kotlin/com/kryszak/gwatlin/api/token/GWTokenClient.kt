package com.kryszak.gwatlin.api.token

import com.kryszak.gwatlin.api.token.model.Token
import com.kryszak.gwatlin.clients.token.TokenClient

/**
 * Client for token info endpoint
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWTokenClient(apiKey: String) {

    private val tokenClient: TokenClient = TokenClient(apiKey)

    /**
     * Retrieves information about token
     */
    fun getTokenInfo(): Token {
        return tokenClient.getTokenInfo()
    }
}
