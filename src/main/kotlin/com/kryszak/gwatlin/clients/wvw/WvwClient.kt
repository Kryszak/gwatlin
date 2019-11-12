package com.kryszak.gwatlin.clients.wvw

import com.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatch
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldOverview
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldScores
import com.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldStats
import com.kryszak.gwatlin.api.wvw.model.objective.WvwObjective
import com.kryszak.gwatlin.api.wvw.model.rank.WvwRank
import com.kryszak.gwatlin.api.wvw.model.upgrade.WvwUpgrade
import com.kryszak.gwatlin.http.BaseHttpClient

internal class WvwClient : BaseHttpClient() {

    private val wvwEndpoint = "wvw"

    fun getAbilityIds(): List<Int> {
        return getRequest("$wvwEndpoint/abilities")
    }

    fun getAbilities(ids: List<Int>, language: String): List<WvwAbility> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/abilities?ids=$params&lang=$language")
    }

    fun getMatchIds(): List<String> {
        return getRequest("$wvwEndpoint/matches")
    }

    fun getMatches(ids: List<String>): List<WvwMatch> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/matches?ids=$params")
    }

    fun getMatchesOverview(id: Int): WvwWorldOverview {
        return getRequest("$wvwEndpoint/matches/overview?world=$id")
    }

    fun getMatchScores(id: Int): WvwWorldScores {
        return getRequest("$wvwEndpoint/matches/scores?world=$id")
    }

    fun getMatchStats(id: Int): WvwWorldStats {
        return getRequest("$wvwEndpoint/matches/stats?world=$id")
    }

    fun getObjectiveIds(): List<String> {
        return getRequest("$wvwEndpoint/objectives")
    }

    fun getObjectives(ids: List<String>, language: String): List<WvwObjective> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/objectives?ids=$params&lang=$language")
    }

    fun getRankIds(): List<Int> {
        return getRequest("$wvwEndpoint/ranks")
    }

    fun getRanks(ids: List<Int>, language: String): List<WvwRank> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/ranks?ids=$params&lang=$language")
    }

    fun getUpgradeIds(): List<Int> {
        return getRequest("$wvwEndpoint/upgrades")
    }

    fun getUpgrades(ids: List<Int>, language: String): List<WvwUpgrade> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/upgrades?ids=$params&lang=$language")
    }
}
