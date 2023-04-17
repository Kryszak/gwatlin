package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("invited")
data class GuildLogInvited(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    override val type: String,
    @SerialName("invited_by")
    val invitedBy: String
) : GuildLog
