package io.github.kryszak.gwatlin.api.guild.model.treasury

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for objects describing which currently in-progress upgrades are needing this guild treasury
 */
@Serializable
data class GuildTreasuryUpgrade(
        @SerialName("upgrade_id") val upgradeId: Int,
        val count: Int
)
