package io.github.kryszak.gwatlin.api.pvp.model.season

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Data model for pvp season object
 */
@Serializable
data class PvpSeason(
    val id: String,
    val name: String,
    @Contextual
    val start: OffsetDateTime? = null,
    @Contextual
    val end: OffsetDateTime? = null,
    val active: Boolean,
    val divisions: List<PvpSeasonDivision> = listOf(),
    val leaderboards: PvpSeasonLeaderboard,
)
