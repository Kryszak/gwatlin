package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.Serializable

/**
 * Shared interface for guild log types
 */
@Serializable
sealed interface GuildLog {
        val id: Int
        val time: String
        val user: String?
        val type: String
}