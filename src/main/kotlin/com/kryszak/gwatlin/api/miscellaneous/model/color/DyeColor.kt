package com.kryszak.gwatlin.api.miscellaneous.model.color

import com.google.gson.annotations.SerializedName

/**
 * Data model for dye color
 */
data class DyeColor(
        val id: Int,
        val name: String,
        @SerializedName("base_rgb") val baseRgb: List<Int>,
        val cloth: ColorDetails,
        val leather: ColorDetails,
        val metal: ColorDetails,
        val fur: ColorDetails?,
        val item: Int,
        val categories: List<String>
)
