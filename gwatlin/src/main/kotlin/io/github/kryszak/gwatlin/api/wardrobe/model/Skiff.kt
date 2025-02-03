package io.github.kryszak.gwatlin.api.wardrobe.model

import io.github.kryszak.gwatlin.api.shared.DyeSlot
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skiffs endpoint response
 */
@Serializable
data class Skiff(
    val id: Int,
    val name: String,
    val icon: String,
    @SerialName("dye_slots")
    val dyeSlots: List<DyeSlot>,
)
