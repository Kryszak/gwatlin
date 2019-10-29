package com.kryszak.gwatlin.api.guild.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for guild object
 */
data class Guild(
        val level: Int,
        @SerializedName("motd") val messageOfTheDay: String,
        val influence: Int,
        val aetherium: String,
        val favor: Int,
        @SerializedName("member_count") val memberCount: Int,
        @SerializedName("member_capacity") val memberCapacity: Int,
        val id: String,
        val name: String,
        val tag: String,
        val emblem: GuildEmblem
)
