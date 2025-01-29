package io.github.kryszak.gwatlin.api.miscellaneous

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.miscellaneous.model.Icon
import io.github.kryszak.gwatlin.api.miscellaneous.model.Novelty
import io.github.kryszak.gwatlin.api.miscellaneous.model.Quaggan
import io.github.kryszak.gwatlin.api.miscellaneous.model.World
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
     * Returns information about novelties that are available in-game
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/novelties)
     */
    @JvmOverloads
    fun getNovelties(language: ApiLanguage? = null): List<Novelty> {
        return miscellaneousClient.getNovelties(language)
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
