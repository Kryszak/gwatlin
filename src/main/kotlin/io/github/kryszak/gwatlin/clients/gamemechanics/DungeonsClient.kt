package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.dungeon.Dungeon
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class DungeonsClient : BaseHttpClient() {

    private val dungeonsEndpoint = "/dungeons"

    fun getDungeons(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Dungeon> {
        return getRequest("$dungeonsEndpoint?ids=all", language)
    }
}