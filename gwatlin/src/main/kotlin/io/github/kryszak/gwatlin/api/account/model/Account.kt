package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Data model for account object
 */
@Serializable
data class Account(
        val id: String,
        val age: Int,
        val name: String,
        val world: Int,
        val guilds: List<String> = listOf(),
        @SerialName("guild_leader") val guildLeader: List<String> = listOf(),
        @Contextual
        val created: OffsetDateTime,
        val access: List<String> = listOf(),
        val commander: Boolean,
        @SerialName("fractal_level") val fractalLevel: Int,
        @SerialName("daily_ap") val dailyAp: Int,
        @SerialName("monthly_ap") val monthlyAp: Int,
        @SerialName("wvw_rank") val wvwRank: Int,
        @SerialName("last_modified") val lastModified: String? = null
)
