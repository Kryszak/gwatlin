package io.github.kryszak.gwatlin.api.characters.model.character.inventory

import io.github.kryszak.gwatlin.api.characters.model.character.ItemStats
import io.github.kryszak.gwatlin.api.shared.ItemBinding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for inventory slots
 */
@Serializable
data class InventorySlot(
    val id: Int,
    val count: Int,
    val charges: Int? = null,
    val infusions: List<Int> = listOf(),
    val upgrades: List<Int> = listOf(),
    @SerialName("upgrade_slot_indices")
    val upgradeSlotIndices: List<Int> = listOf(),
    val skin: Int? = null,
    val stats: ItemStats? = null,
    val dyes: List<Int?> = listOf(),
    val binding: ItemBinding? = null,
    @SerialName("bound_to")
    val boundTo: String? = null
)