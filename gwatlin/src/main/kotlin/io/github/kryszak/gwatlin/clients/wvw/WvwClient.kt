package io.github.kryszak.gwatlin.clients.wvw

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatch
import io.github.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldOverview
import io.github.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldScores
import io.github.kryszak.gwatlin.api.wvw.model.match.overview.WvwWorldStats
import io.github.kryszak.gwatlin.api.wvw.model.objective.WvwObjective
import io.github.kryszak.gwatlin.api.wvw.model.rank.WvwRank
import io.github.kryszak.gwatlin.api.wvw.model.upgrade.WvwUpgrade
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class WvwClient : BaseHttpClient() {

    private val wvwEndpoint = "/wvw"

    fun getAbilityIds(): List<Int> {
        return getRequest("$wvwEndpoint/abilities")
    }

    fun getAbilities(ids: List<Int>, language: ApiLanguage?): List<WvwAbility> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/abilities", listOf("ids" to params), language)
    }

    fun getMatchIds(): List<String> {
        return getRequest("$wvwEndpoint/matches")
    }

    fun getMatches(ids: List<String>): List<WvwMatch> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/matches", listOf("ids" to params))
    }

    fun getMatchesOverview(id: Int): WvwWorldOverview {
        return getRequest("$wvwEndpoint/matches/overview", listOf("world" to id.toString()))
    }

    fun getMatchScores(id: Int): WvwWorldScores {
        return getRequest("$wvwEndpoint/matches/scores", listOf("world" to id.toString()))
    }

    fun getMatchStats(id: Int): WvwWorldStats {
        return getRequest("$wvwEndpoint/matches/stats", listOf("world" to id.toString()))
    }

    fun getObjectiveIds(): List<String> {
        return getRequest("$wvwEndpoint/objectives")
    }

    fun getObjectives(ids: List<String>, language: ApiLanguage?): List<WvwObjective> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/objectives", listOf("ids" to params), language)
    }

    fun getRankIds(): List<Int> {
        return getRequest("$wvwEndpoint/ranks")
    }

    fun getRanks(ids: List<Int>, language: ApiLanguage?): List<WvwRank> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/ranks", listOf("ids" to params), language)
    }

    fun getUpgradeIds(): List<Int> {
        return getRequest("$wvwEndpoint/upgrades")
    }

    fun getUpgrades(ids: List<Int>, language: ApiLanguage?): List<WvwUpgrade> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/upgrades", listOf("ids" to params), language)
    }
}
