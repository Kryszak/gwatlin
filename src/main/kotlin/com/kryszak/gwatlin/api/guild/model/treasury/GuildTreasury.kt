package com.kryszak.gwatlin.api.guild.model.treasury

import com.google.gson.annotations.SerializedName

/**
 * Data model for guild treasury object
 */
data class GuildTreasury(
        @SerializedName("item_id") val itemId: Int,
        val count: Int,
        @SerializedName("needed_by") val neededBy: List<GuildTreasuryUpgrade>
)
