package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.race.Race
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class RacesClient : BaseHttpClient() {

    private val racesEndpoint: String = "/races"

    fun getRacesIds(): List<String> {
        return getRequest(racesEndpoint)
    }

    fun getRace(id: String): Race {
        return getRequest("$racesEndpoint/$id")
    }

    fun getRaces(ids: List<String>): List<Race> {
        val params = ids.joinToString(",")
        return getRequest("$racesEndpoint?ids=$params")
    }
}