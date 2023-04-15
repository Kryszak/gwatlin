package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for inventory object
 */
@Serializable
data class InventoryItem(
        val id: Int,
        val count: Int,
        val charges: Int?,
        val skin: Int?,
        val upgrades: List<Int>?,
        val infusions: List<Int>?,
        val binding: String?
)
