package io.github.kryszak.gwatlin.api.guild.model.team

import io.github.kryszak.gwatlin.api.pvp.model.game.PvpGame
import io.github.kryszak.gwatlin.api.pvp.model.stats.WinLossStats
import kotlinx.serialization.Serializable

/**
 * Data model for guild team object
 */
@Serializable
data class GuildTeam(
        val id: Int,
        val members: List<GuildTeamMember> = listOf(),
        val name: String,
        val aggregate: WinLossStats,
        val ladders: UnrankedStatistics,
        val games: List<PvpGame> = listOf(),
        val seasons: List<GuildTeamSeason> = listOf(),
)
