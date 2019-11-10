package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatch
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

    /**
     * Retrieves all match ids
     */
    fun getMatchIds(): List<String> {
        return wvwClient.getMatchIds()
    }

    /**
     * Returns further details about the specified matches, including the total score, kills and deaths, and further details for each map
     */
    fun getMatches(ids: List<String>): List<WvwMatch> {
        return wvwClient.getMatches(ids)
    }
}