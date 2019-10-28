package com.kryszak.gwatlin.api.achievement.model.daily

import com.google.gson.annotations.SerializedName

/**
 * Data model for daily achievement object
 */
data class DailyAchievement(
        val id: Int,
        val level: DailyAchievementLevel,
        @SerializedName("required_access") val requiredAccess: List<String>
)