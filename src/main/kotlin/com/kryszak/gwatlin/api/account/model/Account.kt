package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for account object
 */
@Serializable
data class Account(
        val id: String,
        val age: Int,
        val name: String,
        val world: Int,
        val guilds: List<String>,
        @SerialName("guild_leader") val guildLeader: List<String>,
        val created: String,
        val access: List<String>,
        val commander: Boolean,
        @SerialName("fractal_level") val fractalLevel: Int,
        @SerialName("daily_ap") val dailyAp: Int,
        @SerialName("monthly_ap") val monthlyAp: Int,
        @SerialName("wvw_rank") val wvwRank: Int,
        @SerialName("last_modified") val lastModified: String? = null
)
