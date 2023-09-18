package io.github.kryszak.gwatlin.api.guild.model.stash

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild stash object
 */
@Serializable
data class GuildStash(
        @SerialName("upgrade_id") val upgradeId: Int,
        val size: Int,
        val coins: Int,
        val note: String,
        val inventory: List<GuildStashItem?>
)
