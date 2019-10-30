package com.kryszak.gwatlin.api.guild.model

/**
 * Data model for guild member object
 */
data class GuildMember(
        val name: String,
        val rank: String,
        val joined: String
)
