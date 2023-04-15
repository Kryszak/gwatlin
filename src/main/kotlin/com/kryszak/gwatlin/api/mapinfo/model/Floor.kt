package com.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.Map

/**
 * Data model for Floors
 */
@Serializable
data class Floor(
    @SerialName("texture_dims")
    val textureDims: Dimensions,
    @SerialName("clamped_view")
    val clampedView: Rectangle,
    val regions: Map<Int, Region>
)
