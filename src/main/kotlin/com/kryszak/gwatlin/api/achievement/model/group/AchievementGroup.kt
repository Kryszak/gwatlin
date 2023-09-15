package com.kryszak.gwatlin.api.achievement.model.group

import kotlinx.serialization.Serializable

/**
 * Data model for achievement group object
 */
@Serializable
data class AchievementGroup(
        val id: String,
        val name: String,
        val description: String,
        val order: Int,
        val categories: List<Int>
)