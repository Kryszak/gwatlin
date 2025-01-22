package io.github.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.Serializable

/**
 * Data model for guild rank object
 */
@Serializable
data class GuildRank(
        val id: String,
        val order: Int,
        val permissions: List<String> = listOf(),
        val icon: String
)
