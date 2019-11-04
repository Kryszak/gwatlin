package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.items.model.skins.Skin
import com.kryszak.gwatlin.clients.items.SkinsClient

/**
 * Client for skins endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWSkinsClient {

    private val skinsClient: SkinsClient = SkinsClient()

    /**
     * Retrieves all skin ids
     */
    fun getSkinIds(): List<Int> {
        return skinsClient.getSkinIds()
    }

    /**
     * Retrieves specific skins
     * @param ids of skins
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.skins.Skin
     */
    fun getSkins(ids: List<Int>, language: String): List<Skin> {
        return skinsClient.getSkins(ids, language)
    }
}