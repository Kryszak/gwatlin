package com.kryszak.gwatlin.api.account.model

import com.google.gson.annotations.SerializedName

/**
 * Data model for account object
 */
data class Account(
        val id: String,
        val age: Int,
        val name: String,
        val world: Int,
        val guilds: List<String>,
        @SerializedName("guild_leader") val guildLeader: List<String>,
        val created: String,
        val access: List<String>,
        val commander: Boolean,
        @SerializedName("fractal_level") val fractalLevel: Int,
        @SerializedName("daily_ap") val dailyAp: Int,
        @SerializedName("monthly_ap") val monthlyAp: Int,
        @SerializedName("wvw_rank") val wvwRank: Int,
        @SerializedName("last_modified") val lastModified: String
)
