package com.kryszak.gwatlin.api.items.model.pvp.amulet

/**
 * Data model for pvp amulet object
 */
data class PvpAmulet(
        val id: Int,
        val name: String,
        val icon: String,
        val attributes: PvpAmuletAttributes
)