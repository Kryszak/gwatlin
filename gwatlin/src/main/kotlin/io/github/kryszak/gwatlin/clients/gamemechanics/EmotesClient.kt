package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.Emote
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class EmotesClient : BaseHttpClient() {

    private val emotesEndpoint: String = "/emotes"

    fun getEmoteIds(): List<String> {
        return getRequest(emotesEndpoint)
    }

    fun getEmote(id: String): Emote {
        return getRequest("$emotesEndpoint/$id")
    }

    fun getEmotes(ids: List<String>): List<Emote> {
        val params = ids.joinToString(",")
        return getRequest(emotesEndpoint, listOf("ids" to params))
    }

    fun getAllEmotes(): List<Emote> {
        return getRequest(emotesEndpoint, listOf("ids" to "all"))
    }
}