package com.kryszak.gwatlin.api.guild.model.team

import com.kryszak.gwatlin.api.pvp.model.game.PvpGame
import com.kryszak.gwatlin.api.pvp.model.stats.WinLossStats
import kotlinx.serialization.Serializable

/**
 * Data model for guild team object
 */
@Serializable
data class GuildTeam(
        val id: Int,
        val members: List<GuildTeamMember>,
        val name: String,
        val aggregate: WinLossStats,
        val ladders: UnrankedStatistics,
        val games: List<PvpGame>,
        val seasons: List<GuildTeamSeason>
)
