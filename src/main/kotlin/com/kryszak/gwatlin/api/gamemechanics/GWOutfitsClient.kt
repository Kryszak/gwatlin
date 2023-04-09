package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.outfit.Outfit
import com.kryszak.gwatlin.clients.gamemechanics.OutfitsClient

/**
 * Client for game mechanic - outfits endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWOutfitsClient {

    private val outfitsClient: OutfitsClient = OutfitsClient()

    /**
     * Retrieves all outfit ids
     * @return list of outfit ids
     */
    fun getOutfitsIds(): List<Int> {
        return outfitsClient.getOutfitsIds()
    }

    /**
     * Retrieves requested outfits
     * @param ids of outfits
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.outfit.Outfit
     */
    @JvmOverloads
    fun getOutfits(ids: List<Int>, language: ApiLanguage? = null): List<Outfit> {
        return outfitsClient.getOutfits(ids, language)
    }

    /**
     * Retrieves all outfits
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.outfit.Outfit
     */
    @JvmOverloads
    fun getAllOutfits(language: ApiLanguage? = null): List<Outfit> {
        return outfitsClient.getAllOutfits(language)
    }
}
