package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.Emote
import io.github.kryszak.gwatlin.clients.gamemechanics.EmotesClient

/**
 * Client for game mechanic - legends endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/emotes).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWEmotesClient {

    private val emotesClient = EmotesClient()

    /**
     * Returns list of all available emote ids
     */
    fun getEmoteIds(): List<String> {
        return emotesClient.getEmoteIds()
    }

    /**
     * Returns requested emote
     * @param id of emote
     */
    fun getEmote(id: String): Emote {
        return emotesClient.getEmote(id)
    }

    /**
     * Returns requested emotes
     * @param ids of emotes
     */
    fun getEmotes(ids: List<String>): List<Emote> {
        return emotesClient.getEmotes(ids)
    }

    /**
     * Returns all available emotes
     */
    fun getAllEmotes(): List<Emote> {
        return emotesClient.getAllEmotes()
    }
}