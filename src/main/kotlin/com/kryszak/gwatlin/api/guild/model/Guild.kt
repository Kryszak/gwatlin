package com.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Data model for guild object
 */
@Serializable
data class Guild(
        val level: Int,
        @SerialName("motd") val messageOfTheDay: String,
        val influence: Int,
        val aetherium: String,
        val favor: Int,
        @SerialName("member_count") val memberCount: Int,
        @SerialName("member_capacity") val memberCapacity: Int,
        val id: String,
        val name: String,
        val tag: String,
        val emblem: GuildEmblem
)
