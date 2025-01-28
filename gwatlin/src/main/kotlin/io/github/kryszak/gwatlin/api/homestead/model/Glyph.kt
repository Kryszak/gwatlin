package io.github.kryszak.gwatlin.api.homestead.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for homestead glyph response
 */
@Serializable
data class Glyph(
    val id: String,
    @SerialName("item_id")
    val itemId: Int,
    val slot: String,
)
