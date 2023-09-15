package com.kryszak.gwatlin.api.miscellaneous

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.miscellaneous.model.*
import com.kryszak.gwatlin.api.miscellaneous.model.color.DyeColor
import com.kryszak.gwatlin.api.miscellaneous.model.dungeon.Dungeon
import com.kryszak.gwatlin.api.miscellaneous.model.raid.Raid
import com.kryszak.gwatlin.clients.miscellaneous.MiscellaneousClient

/**
 * Client for miscellaneous endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMiscellaneousClient {

    private val miscellaneousClient: MiscellaneousClient = MiscellaneousClient()

    /**
     * Returns the current build id of the game.
     */
    fun getBuildId(): Int {
        return miscellaneousClient.getBuildId().id
    }

    /**
     * Returns commonly requested in-game assets
     */
    fun getIcons(): List<Icon> {
        return miscellaneousClient.getIcons()
    }

    /**
     * Returns quaggan images
     */
    fun getQuaggans(): List<Quaggan> {
        return miscellaneousClient.getQuaggans()
    }

    /**
     * Returns all dye colors in the game, including localized names and their color component information
     */
    @JvmOverloads
    fun getDyeColors(language: ApiLanguage? = null): List<DyeColor> {
        return miscellaneousClient.getColors(language)
    }

    /**
     * Returns a list of the currencies contained in the account wallet
     */
    @JvmOverloads
    fun getCurrencies(language: ApiLanguage? = null): List<Currency> {
        return miscellaneousClient.getCurrencies(language)
    }

    /**
     * Returns details about each dungeon and it's associated paths
     */
    @JvmOverloads
    fun getDungeons(language: ApiLanguage? = null): List<Dungeon> {
        return miscellaneousClient.getDungeons(language)
    }

    /**
     * Returns all minis in the game
     */
    @JvmOverloads
    fun getMinis(language: ApiLanguage? = null): List<Mini> {
        return miscellaneousClient.getMinis(language)
    }

    /**
     * Returns information about novelties that are available in-game
     */
    @JvmOverloads
    fun getNovelties(language: ApiLanguage? = null): List<Novelty> {
        return miscellaneousClient.getNovelties(language)
    }

    /**
     * Resource returns details about each raid and it's associated wings
     */
    @JvmOverloads
    fun getRaids(language: ApiLanguage? = null): List<Raid> {
        return miscellaneousClient.getRaids(language)
    }

    /**
     * Returns information about the titles that are in the game
     */
    @JvmOverloads
    fun getTitles(language: ApiLanguage? = null): List<Title> {
        return miscellaneousClient.getTitles(language)
    }

    /**
     * Returns information about the available worlds, or servers
     */
    @JvmOverloads
    fun getWorlds(language: ApiLanguage? = null): List<World> {
        return miscellaneousClient.getWorlds(language)
    }
}
