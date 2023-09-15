package com.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Serializable

/**
 * Data model for WvW ability progress
 */
@Serializable
data class WvwAbility(
    val id: Int,
    val rank: Int
)