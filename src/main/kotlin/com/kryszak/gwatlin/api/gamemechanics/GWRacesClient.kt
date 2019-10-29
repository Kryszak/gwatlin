package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.race.Race
import com.kryszak.gwatlin.clients.gamemechanics.RacesClient

/**
 * Client for game mechanic - races endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see com.kryszak.gwatlin.api.gamemechanics.model.race.Race
     */
    fun getRace(id: String): Race {
        return racesClient.getRace(id)
    }
}