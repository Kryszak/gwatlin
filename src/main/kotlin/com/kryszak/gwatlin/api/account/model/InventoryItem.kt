package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for inventory object
 */
@Serializable
data class InventoryItem(
        val id: Int,
        val count: Int,
        val charges: Int? = null,
        val skin: Int? = null,
        val upgrades: List<Int> = listOf(),
        val infusions: List<Int> = listOf(),
        val binding: String? = null
)
