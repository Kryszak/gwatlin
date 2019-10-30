package com.kryszak.gwatlin.api.guild.model.treasury

import com.google.gson.annotations.SerializedName

/**
 * Data model for objects describing which currently in-progress upgrades are needing this guild treasury
 */
data class GuildTreasuryUpgrade(
        @SerializedName("upgrade_id") val upgradeId: Int,
        val count: Int
)
