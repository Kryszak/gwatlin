package io.github.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.Serializable

/**
 * Data model for guild member object
 */
@Serializable
data class GuildMember(
        val name: String,
        val rank: String,
        val joined: String
)
