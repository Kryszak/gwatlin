package io.github.kryszak.gwatlin.api.miscellaneous

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.miscellaneous.model.*
import io.github.kryszak.gwatlin.api.miscellaneous.model.color.DyeColor
import io.github.kryszak.gwatlin.api.miscellaneous.model.dungeon.Dungeon
import io.github.kryszak.gwatlin.api.miscellaneous.model.raid.Raid
import io.github.kryszak.gwatlin.clients.miscellaneous.MiscellaneousClient

/**
 * Client for miscellaneous endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMiscellaneousClient {

    private val miscellaneousClient: MiscellaneousClient = MiscellaneousClient()

    /**
     * Returns the current build id of the game.
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/build)
     */
    fun getBuildId(): Int {
        return miscellaneousClient.getBuildId().id
    }

    /**
     * Returns commonly requested in-game assets
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/files)
     */
    fun getIcons(): List<Icon> {
        return miscellaneousClient.getIcons()
    }

    /**
     * Returns quaggan images
     * * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/quaggans)
     */
    fun getQuaggans(): List<Quaggan> {
        return miscellaneousClient.getQuaggans()
    }

    /**
     * Returns all dye colors in the game, including localized names and their color component information
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dyes)
     */
    @JvmOverloads
    fun getDyeColors(language: ApiLanguage? = null): List<DyeColor> {
        return miscellaneousClient.getColors(language)
    }

    /**
     * Returns a list of the currencies contained in the account wallet
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/currencies)
     */
    @JvmOverloads
    fun getCurrencies(language: ApiLanguage? = null): List<Currency> {
        return miscellaneousClient.getCurrencies(language)
    }

    /**
     * Returns details about each dungeon and it's associated paths
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dungeons)
     */
    @JvmOverloads
    fun getDungeons(language: ApiLanguage? = null): List<Dungeon> {
        return miscellaneousClient.getDungeons(language)
    }

    /**
     * Returns all minis in the game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/minis)
     */
    @JvmOverloads
    fun getMinis(language: ApiLanguage? = null): List<Mini> {
        return miscellaneousClient.getMinis(language)
    }

    /**
     * Returns information about novelties that are available in-game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/novelties)
     */
    @JvmOverloads
    fun getNovelties(language: ApiLanguage? = null): List<Novelty> {
        return miscellaneousClient.getNovelties(language)
    }

    /**
     * Resource returns details about each raid and it's associated wings
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/raids)
     */
    @JvmOverloads
    fun getRaids(language: ApiLanguage? = null): List<Raid> {
        return miscellaneousClient.getRaids(language)
    }

    /**
     * Returns information about the titles that are in the game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/titles)
     */
    @JvmOverloads
    fun getTitles(language: ApiLanguage? = null): List<Title> {
        return miscellaneousClient.getTitles(language)
    }

    /**
     * Returns information about the available worlds, or servers
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/worlds)
     */
    @JvmOverloads
    fun getWorlds(language: ApiLanguage? = null): List<World> {
        return miscellaneousClient.getWorlds(language)
    }
}
