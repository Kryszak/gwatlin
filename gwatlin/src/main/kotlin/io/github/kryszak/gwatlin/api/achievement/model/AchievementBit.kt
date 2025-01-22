package io.github.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.Serializable

/**
 * Data mode for achievement bits. Can give further information on the progress towards the achievement.
 */
@Serializable
data class AchievementBit(
    val type: AchievementBitType? = null,
    val id: Int? = null,
    val text: String? = null
)
