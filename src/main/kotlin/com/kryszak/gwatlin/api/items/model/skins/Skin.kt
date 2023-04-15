package com.kryszak.gwatlin.api.items.model.skins

import kotlinx.serialization.Serializable

/**
 * Data model for skin object
 */
@Serializable
data class Skin(
        val id: Int,
        val name: String,
        val type: String,
        val flags: List<String>,
        val restrictions: List<String>,
        val icon: String,
        val rarity: String,
        val description: String
)