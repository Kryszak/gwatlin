package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.raid.Raid
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class RaidClient : BaseHttpClient() {

    private val raidsEndpoint = "/raids"

    fun getRaids(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Raid> {
        return getRequest("$raidsEndpoint?ids=all", language)
    }
}