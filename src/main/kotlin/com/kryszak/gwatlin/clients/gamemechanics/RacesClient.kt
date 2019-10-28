package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.race.Race
import com.kryszak.gwatlin.http.BaseHttpClient

internal class RacesClient : BaseHttpClient() {

    private val racesEndpoint: String = "races"

    fun getRacesIds(): List<String> {
        return getRequest(racesEndpoint)
    }

    fun getRace(id: String): Race {
        return getRequest("$racesEndpoint/$id")
    }
}