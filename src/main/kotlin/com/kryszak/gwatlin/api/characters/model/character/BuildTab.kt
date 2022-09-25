package com.kryszak.gwatlin.api.characters.model.character

import com.google.gson.annotations.SerializedName

/**
 * Data model for BuildTabs
 */
data class BuildTab(
    val tab: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    val build: Build
)