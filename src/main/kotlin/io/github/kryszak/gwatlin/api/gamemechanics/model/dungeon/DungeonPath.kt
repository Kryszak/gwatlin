package io.github.kryszak.gwatlin.api.gamemechanics.model.dungeon

import kotlinx.serialization.Serializable

/**
 * Data model for dungeon path
 */
@Serializable
data class DungeonPath(
        val id: String,
        val type: String
)
