package io.github.kryszak.gwatlin.api.achievement.model.category

import kotlinx.serialization.Serializable

/**
 * Data model for achievement category object
 */
@Serializable
data class AchievementCategory(
        val id: Int,
        val name: String,
        val description: String,
        val order: Int,
        val icon: String,
        val achievements: List<Int> = listOf(),
)