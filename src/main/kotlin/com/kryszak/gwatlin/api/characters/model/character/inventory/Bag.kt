package com.kryszak.gwatlin.api.characters.model.character.inventory

data class Bag(
    val id: Int,
    val size: Int,
    val inventory: List<InventorySlot>
)