package com.kryszak.gwatlin.api.miscellaneous.model.dungeon

import kotlinx.serialization.Serializable

/**
 * Data model for dungeon path
 */
@Serializable
data class DungeonPath(
        val id: String,
        val type: String
)
