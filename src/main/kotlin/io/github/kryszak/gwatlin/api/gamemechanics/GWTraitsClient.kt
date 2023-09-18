package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import io.github.kryszak.gwatlin.clients.gamemechanics.TraitsClient

/**
 * Client for game mechanic - traits endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
     */
    @JvmOverloads
    fun getTraits(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Trait> {
        return traitsClient.getTraits(ids, language)
    }
}
