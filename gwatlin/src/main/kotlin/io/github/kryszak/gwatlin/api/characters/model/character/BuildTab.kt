package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for BuildTabs
 */
@Serializable
data class BuildTab(
    val tab: Int,
    @SerialName("is_active")
    val isActive: Boolean,
    val build: Build
)