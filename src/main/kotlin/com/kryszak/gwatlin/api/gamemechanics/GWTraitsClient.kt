package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import com.kryszak.gwatlin.clients.gamemechanics.TraitsClient

/**
 * Client for game mechanic - traits endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWTraitsClient {

    private val traitsClient: TraitsClient = TraitsClient()

    /**
     * Retrieves list of all trait ids
     */
    fun getTraitIds(): List<Int> {
        return traitsClient.getTraitIds()
    }

    /**
     * Retrieves specific traits
     * @param ids of traits
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
     */
    fun getTraits(ids: List<Int>, language: String = "en"): List<Trait> {
        return traitsClient.getTraits(ids, language)
    }
}
