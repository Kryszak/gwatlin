package com.kryszak.gwatlin.api.guild.model.upgrade

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for guild upgrade object
 */
@Serializable
data class GuildUpgrade(
        val id: Int,
        val name: String,
        val description: String,
        val type: String,
        val icon: String,
        @SerialName("build_time") val buildTime: Int,
        @SerialName("required_level") val requiredLevel: Int,
        val experience: Int,
        val prerequisites: List<Int>,
        val costs: List<UpgradeCost>
)
