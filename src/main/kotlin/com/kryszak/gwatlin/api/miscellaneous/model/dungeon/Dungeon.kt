package com.kryszak.gwatlin.api.miscellaneous.model.dungeon

/**
 * Data model for dungeon object
 */
data class Dungeon(
        val id: String,
        val paths: List<DungeonPath>
)
