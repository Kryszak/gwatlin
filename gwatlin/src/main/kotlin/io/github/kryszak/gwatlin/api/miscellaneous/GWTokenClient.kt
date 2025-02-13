package io.github.kryszak.gwatlin.api.miscellaneous

import io.github.kryszak.gwatlin.api.miscellaneous.model.Token
import io.github.kryszak.gwatlin.clients.miscellaneous.TokenClient

/**
 * Client for token info endpoint. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/tokeninfo)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
