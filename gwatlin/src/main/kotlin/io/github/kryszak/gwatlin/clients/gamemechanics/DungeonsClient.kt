package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.dungeon.Dungeon
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class DungeonsClient : BaseHttpClient() {

    private val dungeonsEndpoint = "/dungeons"

    fun getDungeons(language: ApiLanguage?): List<Dungeon> {
        return getRequest("$dungeonsEndpoint?ids=all", listOf(), language)
    }

    fun getDungeonIds(): List<String> {
        return getRequest(dungeonsEndpoint)
    }

    fun getDungeon(id: String, language: ApiLanguage?): Dungeon {
        return getRequest("$dungeonsEndpoint/$id", listOf(), language)
    }

    fun getDungeons(ids: List<String>, language: ApiLanguage?): List<Dungeon> {
        val params = ids.joinToString(",")
        return getRequest("$dungeonsEndpoint?ids=$params", listOf(), language)
    }
}