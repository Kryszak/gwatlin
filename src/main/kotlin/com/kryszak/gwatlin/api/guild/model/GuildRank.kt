package com.kryszak.gwatlin.api.guild.model

/**
 * Data model for guild rank object
 */
data class GuildRank(
        val id: String,
        val order: Int,
        val permissions: List<String>,
        val icon: String
)
