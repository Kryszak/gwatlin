package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.Mastery
import com.kryszak.gwatlin.clients.gamemechanics.GameMechanicsClient

/**
 * Client for game mechanics endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWGameMechanicsClient {

    private val gameMechanicsClient: GameMechanicsClient = GameMechanicsClient()

    /**
     * Retrieves list of all mastery ids
     * @return mastery ids list
     */
    fun getMasteriesIds(): List<Int> {
        return gameMechanicsClient.getMasteriesIds()
    }

    /**
     * Retrieves specific mastery
     * @param id of mastery
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.Mastery
     */
    fun getMastery(id: Int, language: String = "en"): Mastery {
        return gameMechanicsClient.getMastery(id, language)
    }

    /**
     * Retrieves list of masteries
     * @param ids list of mastery ids
     * @param language of returned text (default=en)
     */
    fun getMasteries(ids: List<Int>, language: String = "en"): List<Mastery> {
        return gameMechanicsClient.getMasteries(ids, language)
    }
}