package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatch
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldOverview
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldScores
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldStats
import com.kryszak.gwatlin.api.wvw.model.objective.WvwObjective
import com.kryszak.gwatlin.api.wvw.model.rank.WvwRank
import com.kryszak.gwatlin.api.wvw.model.upgrade.WvwUpgrade
import com.kryszak.gwatlin.clients.wvw.WvwClient

/**
 * Client for wvw endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWWvwClient {

    private val wvwClient: WvwClient = WvwClient()

    /**
     * Retrieves all ability ids
     */
    fun getAbilityIds(): List<Int> {
        return wvwClient.getAbilityIds()
    }

    /**
     * Returns information about the available abilities in the World versus World game mode
     */
    fun getAbilities(ids: List<Int>, language: ApiLanguage? = null): List<WvwAbility> {
        return wvwClient.getAbilities(ids, language)
    }

    /**
     * Retrieves all match ids
     */
    fun getMatchIds(): List<String> {
        return wvwClient.getMatchIds()
    }

    /**
     * Returns further details about the specified matches, including the total score, kills and deaths, and further details for each map
     */
    fun getMatches(ids: List<String>): List<WvwMatch> {
        return wvwClient.getMatches(ids)
    }

    /**
     * Lists the world ids for each matches along with the start and end times
     */
    fun getMatchesOverview(worldId: Int): WvwWorldOverview {
        return wvwClient.getMatchesOverview(worldId)
    }

    /**
     * Returns a detailed object of scores per map along with the overall score
     */
    fun getMatchScores(worldId: Int): WvwWorldScores {
        return wvwClient.getMatchScores(worldId)
    }

    /**
     * Returns information on map specific, and overall, kills and deaths
     */
    fun getMatchStats(worldId: Int): WvwWorldStats {
        return wvwClient.getMatchStats(worldId)
    }

    /**
     * Retrieves list of all objective ids
     */
    fun getObjectiveIds() :List<String> {
        return wvwClient.getObjectiveIds()
    }

    /**
     * Returns details about World vs. World objectives such as camps, towers, and keeps
     */
    fun getObjectives(ids: List<String>, language: ApiLanguage? = null): List<WvwObjective> {
        return wvwClient.getObjectives(ids, language)
    }

    /**
     * Retrieves all wvw rank ids
     */
    fun getRankIds(): List<Int> {
        return wvwClient.getRankIds()
    }

    /**
     * Returns information about the available ranks in the World versus World game mode
     */
    fun getRanks(ids: List<Int>, language: ApiLanguage? = null): List<WvwRank> {
        return wvwClient.getRanks(ids, language)
    }

    /**
     * Retrieves all wvw upgrade ids
     */
    fun getUpgradeIds(): List<Int> {
        return wvwClient.getUpgradeIds()
    }

    /**
     * Returns details about available World vs. World upgrades for objectives such as camps, towers, and keeps
     */
    fun getUpgrades(ids: List<Int>, language: ApiLanguage? = null): List<WvwUpgrade> {
        return wvwClient.getUpgrades(ids, language)
    }
}
