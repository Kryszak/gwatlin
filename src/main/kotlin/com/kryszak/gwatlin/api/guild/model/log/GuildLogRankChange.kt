package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "rank_change"
 */
@Serializable
@SerialName("rank_change")
data class GuildLogRankChange(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    override val type: String,
    @SerialName("rank_change")
    val rankChange: String,
    @SerialName("old_rank")
    val oldRank: String,
    @SerialName("new_rank")
    val newRank: String
) : GuildLog
