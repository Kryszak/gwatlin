package com.kryszak.gwatlin.api.model.achievement.group

/**
 * Data model for achievement group object
 */
data class AchievementGroup(val id: String,
                            val name: String,
                            val description: String,
                            val order: Int,
                            val categories: List<Int>)