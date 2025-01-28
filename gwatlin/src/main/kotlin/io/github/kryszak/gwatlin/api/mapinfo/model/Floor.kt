package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.Map

/**
 * Data model for Floors
 */
@Serializable
data class Floor(
    val id: Int,
    @SerialName("texture_dims")
    val textureDims: Dimensions,
    @SerialName("clamped_view")
    val clampedView: Rectangle? = null,
    val regions: Map<Int, Region> = mapOf(),
)
