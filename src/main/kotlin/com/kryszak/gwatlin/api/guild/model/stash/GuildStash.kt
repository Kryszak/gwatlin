package com.kryszak.gwatlin.api.guild.model.stash

import com.google.gson.annotations.SerializedName

/**
 * Data model for guild stash object
 */
data class GuildStash(
        @SerializedName("upgrade_id") val upgradeId: Int,
        val size: Int,
        val coins: Int,
        val note: String,
        val inventory: List<GuildStashItem?>
)
