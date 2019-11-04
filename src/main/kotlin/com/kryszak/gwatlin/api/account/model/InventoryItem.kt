package com.kryszak.gwatlin.api.account.model

/**
 * Data model for inventory object
 */
data class InventoryItem(
        val id: Int,
        val count: Int,
        val charges: Int?,
        val skin: Int?,
        val upgrades: List<Int>?,
        val infusions: List<Int>?,
        val binding: String?
)
