package io.github.kryszak.gwatlin.api.guild.model.log

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
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
    @SerialName("rank_change")
    val rankChange: String,
    @SerialName("old_rank")
    val oldRank: String,
    @SerialName("new_rank")
    val newRank: String
) : GuildLog {
    override val type: String by serialNameDelegate
}
