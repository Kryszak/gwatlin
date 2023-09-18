package io.github.kryszak.gwatlin.api.guild.model.treasury

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild treasury object
 */
@Serializable
data class GuildTreasury(
        @SerialName("item_id") val itemId: Int,
        val count: Int,
        @SerialName("needed_by") val neededBy: List<GuildTreasuryUpgrade>
)
