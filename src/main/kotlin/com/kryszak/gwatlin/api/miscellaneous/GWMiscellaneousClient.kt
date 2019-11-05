package com.kryszak.gwatlin.api.miscellaneous

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
    fun getQuaggans(): List<Icon> {
        return miscellaneousClient.getQuaggans()
    }

    /**
     * Returns all dye colors in the game, including localized names and their color component information
     */
    fun getDyeColors(language: String = "en"): List<DyeColor> {
        return miscellaneousClient.getColors(language)
    }

    /**
     * Returns a list of the currencies contained in the account wallet
     */
    fun getCurrencies(language: String = "en"): List<Currency> {
        return miscellaneousClient.getCurrencies(language)
    }

    /**
     * Returns details about each dungeon and it's associated paths
     */
    fun getDungeons(language: String = "en"): List<Dungeon> {
        return miscellaneousClient.getDungeons(language)
    }

    /**
     * Returns all minis in the game
     */
    fun getMinis(language: String = "en"): List<Mini> {
        return miscellaneousClient.getMinis(language)
    }

    /**
     * Returns information about novelties that are available in-game
     */
    fun getNovelties(language: String = "en"): List<Novelty> {
        return miscellaneousClient.getNovelties(language)
    }

    /**
     * Resource returns details about each raid and it's associated wings
     */
    fun getRaids(language: String = "en"): List<Raid> {
        return miscellaneousClient.getRaids(language)
    }

    /**
     * Returns information about the titles that are in the game
     */
    fun getTitles(language: String = "en"): List<Title> {
        return miscellaneousClient.getTitles(language)
    }

    /**
     * Returns information about the available worlds, or servers
     */
    fun getWorlds(language: String = "en"): List<World> {
        return miscellaneousClient.getWorlds(language)
    }
}
