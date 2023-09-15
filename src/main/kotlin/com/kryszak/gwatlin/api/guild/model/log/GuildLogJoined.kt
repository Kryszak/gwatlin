package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "joined"
 */
@Serializable
@SerialName("joined")
data class GuildLogJoined(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    override val type: String
) : GuildLog
