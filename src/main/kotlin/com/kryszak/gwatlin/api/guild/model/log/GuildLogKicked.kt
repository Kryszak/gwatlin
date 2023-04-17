package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("kicked")
data class GuildLogKicked(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    override val type: String,
    @SerialName("kicked_by")
    val kickedBy: String
) : GuildLog
