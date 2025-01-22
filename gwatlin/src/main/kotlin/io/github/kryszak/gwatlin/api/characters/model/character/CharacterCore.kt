package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for core character infos
 */
@Serializable
data class CharacterCore(
    val name: String,
    val race: String,
    val gender: String,
    val profession: String,
    val level: Int,
    val guild: String? = null,
    val age: Int,
    val created: String,
    @SerialName("last_modified")
    val lastModified: String? = null,
    val deaths: Int,
    val title: Int? = null
)
