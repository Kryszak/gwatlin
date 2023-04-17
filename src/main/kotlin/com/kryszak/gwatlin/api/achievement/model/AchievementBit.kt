package com.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.Serializable

@Serializable
data class AchievementBit(
    val type: AchievementBitType,
    val id: Int? = null,
    val text: String? = null
)
