package io.github.kryszak.gwatlin.api.miscellaneous.model.dungeon

import kotlinx.serialization.Serializable

/**
 * Data model for dungeon object
 */
@Serializable
data class Dungeon(
        val id: String,
        val paths: List<DungeonPath> = listOf(),
)
