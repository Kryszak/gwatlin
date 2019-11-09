package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import com.kryszak.gwatlin.clients.wvw.WvwClient

/**
 * Client for wvw endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWWvwClient {

    private val wvwClient: WvwClient = WvwClient()

    /**
     * Retrieves all ability ids
     */
    fun getAbilityIds(): List<Int> {
        return wvwClient.getAbilityIds()
    }

    /**
     * Returns information about the available abilities in the World versus World game mode
     */
    fun getAbilities(ids: List<Int>, language: String = "en"): List<WvwAbility> {
        return wvwClient.getAbilities(ids, language)
    }
}