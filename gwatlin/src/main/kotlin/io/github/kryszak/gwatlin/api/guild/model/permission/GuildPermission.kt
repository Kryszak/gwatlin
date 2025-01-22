package io.github.kryszak.gwatlin.api.guild.model.permission

import kotlinx.serialization.Serializable

/**
 * Data model for guild permission object
 */
@Serializable
data class GuildPermission(
        val id: String,
        val name: String,
        val description: String
)
