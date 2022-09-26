package com.kryszak.gwatlin.api.characters.model.character.inventory

/**
 * Data model for inventory bags
 */
data class Bag(
    val id: Int,
    val size: Int,
    val inventory: List<InventorySlot>
)