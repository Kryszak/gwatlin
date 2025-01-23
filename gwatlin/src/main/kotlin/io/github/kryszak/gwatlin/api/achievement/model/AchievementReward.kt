package io.github.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.Serializable

/**
 * Data model for achievement reward object
 */
@Serializable
data class AchievementReward(
        val id: Int? = null,
        val type: String,
        val region: String? = null,
        val count: Int? = null
)