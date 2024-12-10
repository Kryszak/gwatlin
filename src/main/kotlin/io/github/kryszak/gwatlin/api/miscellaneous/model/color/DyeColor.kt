package io.github.kryszak.gwatlin.api.miscellaneous.model.color

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for dye color
 */
@Serializable
data class DyeColor(
        val id: Int,
        val name: String,
        @SerialName("base_rgb") val baseRgb: List<Int> = listOf(),
        val cloth: ColorDetails,
        val leather: ColorDetails,
        val metal: ColorDetails,
        val fur: ColorDetails? = null,
        val item: Int? = null,
        val categories: List<String> = listOf(),
)
