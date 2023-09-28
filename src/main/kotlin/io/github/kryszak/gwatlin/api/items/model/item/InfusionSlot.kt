package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for infusion slot
 */
@Serializable
data class InfusionSlot(
    val flags: List<String>,
    @SerialName("item_id") val itemId: Int
)