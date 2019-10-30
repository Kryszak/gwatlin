package com.kryszak.gwatlin.api.guild.model.upgrade

import com.google.gson.annotations.SerializedName

/**
 * Data model for guild upgrade object
 */
data class GuildUpgrade(
        val id: Int,
        val name: String,
        val description: String,
        val type: String,
        val icon: String,
        @SerializedName("build_time") val buildTime: Int,
        @SerializedName("required_level") val requiredLevel: Int,
        val experience: Int,
        val prerequisites: List<Int>,
        val costs: List<UpgradeCost>
)
