package io.github.kryszak.gwatlin.api.pvp.model.amulet

import kotlinx.serialization.Serializable

/**
 * Data model for pvp amulet object
 */
@Serializable
data class PvpAmulet(
        val id: Int,
        val name: String,
        val icon: String,
        val attributes: PvpAmuletAttributes
)