package com.kryszak.gwatlin.api.achievement.model.category

/**
 * Data model for achievement category object
 */
data class AchievementCategory(
        val id: Int,
        val name: String,
        val description: String,
        val order: Int,
        val icon: String,
        val achievements: List<Int>
)