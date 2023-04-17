package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.Serializable

/**
 * Data model for guild log object
 */
@Serializable
sealed interface GuildLog {
        val id: Int
        val time: String
        val user: String?
        val type: String
}