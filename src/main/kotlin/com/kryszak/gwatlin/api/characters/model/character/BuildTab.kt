package com.kryszak.gwatlin.api.characters.model.character

import com.google.gson.annotations.SerializedName

data class BuildTab(
    val tab: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    val build: Build
)