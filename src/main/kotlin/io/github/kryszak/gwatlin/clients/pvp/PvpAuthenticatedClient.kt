package io.github.kryszak.gwatlin.clients.pvp

import io.github.kryszak.gwatlin.api.pvp.model.game.PvpGame
import io.github.kryszak.gwatlin.api.pvp.model.stats.PvpStanding
import io.github.kryszak.gwatlin.api.pvp.model.stats.PvpStats
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class PvpAuthenticatedClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val pvpEndpoint = "pvp"

    fun getPvpStats(): PvpStats {
        return getRequestAuth("$pvpEndpoint/stats")
    }

    fun getPvpGameIds(): List<String> {
        return getRequestAuth("$pvpEndpoint/games")
    }

    fun getPvpGames(ids: List<String>): List<PvpGame> {
        val params = ids.joinToString(",")
        return getRequestAuth("$pvpEndpoint/games?ids=$params")
    }

    fun getPvpStandings(): PvpStanding {
        return getRequestAuth("$pvpEndpoint/standings")
    }
}
