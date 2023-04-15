package com.kryszak.gwatlin.api.guild.model.team

import kotlinx.serialization.Serializable

/**
 * Data model for season object
 */
@Serializable
data class GuildTeamSeason(
        val id: String,
        val wins: Int,
        val losses: Int,
        val rating: Int
)
