package com.kryszak.gwatlin.api.guild.model.team

/**
 * Data model for season object
 */
data class GuildTeamSeason(
        val id: String,
        val wins: Int,
        val losses: Int,
        val rating: Int
)
