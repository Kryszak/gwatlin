package com.kryszak.gwatlin.api.guild.model.log

/**
 * Data model for guild log object
 */
data class GuildLog(
        val id: Int,
        val time: String,
        val user: String,
        val type: String
)