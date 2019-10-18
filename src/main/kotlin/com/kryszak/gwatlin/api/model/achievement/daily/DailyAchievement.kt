package com.kryszak.gwatlin.api.model.achievement.daily

import com.google.gson.annotations.SerializedName

data class DailyAchievement(val id: Int,
                            val level: DailyAchievementLevel,
                            @SerializedName(value = "required_access") val requiredAccess: List<String>)