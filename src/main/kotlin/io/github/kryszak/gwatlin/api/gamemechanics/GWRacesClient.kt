package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.race.Race
import io.github.kryszak.gwatlin.clients.gamemechanics.RacesClient

/**
 * Client for game mechanic - races endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/races).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWRacesClient {

    private val racesClient: RacesClient = RacesClient()

    /**
     * Retrieves all race ids
     * @return list of race ids
     */
    fun getRaceIds(): List<String> {
        return racesClient.getRacesIds()
    }

    /**
     * Retrieves specific race
     * @param id of race
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.race.Race
     */
    fun getRace(id: String): Race {
        return racesClient.getRace(id)
    }
}