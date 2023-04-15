package com.kryszak.gwatlin.api.guild.model.team

import kotlinx.serialization.Serializable

/**
 * Data model for guild team member object
 */
@Serializable
data class GuildTeamMember(
        val name: String,
        val role: String
)
