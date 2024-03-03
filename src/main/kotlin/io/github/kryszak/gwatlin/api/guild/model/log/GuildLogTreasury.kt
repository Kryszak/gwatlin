package io.github.kryszak.gwatlin.api.guild.model.log

import io.github.kryszak.gwatlin.http.serializers.SerialNameDelegate.Companion.serialNameDelegate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild log objects with type "treasury"
 */
@Serializable
@SerialName("treasury")
data class GuildLogTreasury(
    override val id: Int,
    override val time: String,
    override val user: String? = null,
    @SerialName("item_id")
    val itemId: Int,
    val count: Int,
) : GuildLog {
    override val type: String by serialNameDelegate
}
