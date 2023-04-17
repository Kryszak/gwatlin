package com.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Data model for guild object
 */
@Serializable
data class Guild(
        val level: Int? = null,
        @SerialName("motd") val messageOfTheDay: String? = null,
        val influence: Int? = null,
        val aetherium: String? = null,
        val favor: Int? = null,
        @SerialName("member_count") val memberCount: Int? = null,
        @SerialName("member_capacity") val memberCapacity: Int? = null,
        val id: String,
        val name: String,
        val tag: String,
        val emblem: GuildEmblem
)
