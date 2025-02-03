package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.model.JadeBot
import io.github.kryszak.gwatlin.clients.wardrobe.JadeBotsClient

/**
 * Client for minis endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/jadebots)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWJadeBotsClient {

    private val jadeBotsClient = JadeBotsClient()

    /**
     * Returns ids of all available jade bots
     */
    fun getJadeBotIds(): List<Int> {
        return jadeBotsClient.getJadeBotIds()
    }

    /**
     * Returns jade bot for given
     * @param id of jade bot
     * @param language for response
     */
    fun getJadeBot(id: Int, language: ApiLanguage? = null): JadeBot {
        return jadeBotsClient.getJadeBot(id, language)
    }

    /**
     * Returns jade bots for given
     * @param ids of jade bots
     * @param language for response
     */
    fun getJadeBots(ids: List<Int>, language: ApiLanguage? = null): List<JadeBot> {
        return jadeBotsClient.getJadeBots(ids, language)
    }
}