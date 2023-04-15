package com.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.Serializable

/**
 * Data model for SAB songs
 */
@Serializable
data class SabSong(
    val id: Int,
    val name: String?
)