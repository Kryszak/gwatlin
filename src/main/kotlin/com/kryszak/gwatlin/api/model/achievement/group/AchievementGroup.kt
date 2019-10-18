package com.kryszak.gwatlin.api.model.achievement.group

data class AchievementGroup(val id: String,
                            val name: String,
                            val description: String,
                            val order: Int,
                            val categories: List<Int>)