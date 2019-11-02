package com.kryszak.gwatlin.api.items.model.skins

/**
 * DAtamodel for skin object
 */
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