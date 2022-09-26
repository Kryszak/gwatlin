package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.Map

data class Floor(
    @SerializedName("texture_dims")
    val textureDims: Dimensions,
    @SerializedName("clamped_view")
    val clampedView: Rectangle,
    val regions: Map<Int, Region>
)
