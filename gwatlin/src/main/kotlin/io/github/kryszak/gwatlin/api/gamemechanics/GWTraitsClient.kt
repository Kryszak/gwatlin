package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import io.github.kryszak.gwatlin.clients.gamemechanics.TraitsClient

/**
 * Client for game mechanic - traits endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/traits).
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
     * Retrieves specific trait
     * @param id of trait
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
     */
    fun getTrait(id: Int, language: ApiLanguage? = null): Trait {
        return traitsClient.getTrait(id, language)
    }

    /**
     * Retrieves specific traits
     * @param ids of traits
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
     */
    @JvmOverloads
    fun getTraits(ids: List<Int>, language: ApiLanguage? = null): List<Trait> {
        return traitsClient.getTraits(ids, language)
    }
}
