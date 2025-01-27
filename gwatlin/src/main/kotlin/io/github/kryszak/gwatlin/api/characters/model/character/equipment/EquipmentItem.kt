package io.github.kryszak.gwatlin.api.characters.model.character.equipment

import io.github.kryszak.gwatlin.api.characters.model.character.ItemStats
import io.github.kryszak.gwatlin.api.shared.ItemSlot
import io.github.kryszak.gwatlin.api.shared.ItemBinding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for equipment items
 */
@Serializable
data class EquipmentItem(
    val id: Int,
    val slot: ItemSlot? = null,
    val binding: ItemBinding? = null,
    @SerialName("bound_to")
    val boundTo: String? = null,
    val infusions: List<Int> = listOf(),
    val location: EquipmentItemLocation? = null,
    val skin: Int? = null,
    val upgrades: List<Int> = listOf(),
    val stats: ItemStats? = null,
    val dyes: List<Int?> = listOf(),
    val charges: Int? = null,
    val tabs: Set<Int> = setOf(),
    val count: Int? = null
)