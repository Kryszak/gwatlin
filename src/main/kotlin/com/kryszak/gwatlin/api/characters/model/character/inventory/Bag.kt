package com.kryszak.gwatlin.api.characters.model.character.inventory

import kotlinx.serialization.Serializable

/**
 * Data model for inventory bags
 */
@Serializable
data class Bag(
    val id: Int,
    val size: Int,
    val inventory: List<InventorySlot?>
)