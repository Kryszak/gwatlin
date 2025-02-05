package io.github.kryszak.gwatlin.api.characters.model.character

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

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
    @Contextual
    val created: OffsetDateTime,
    @Contextual
    @SerialName("last_modified")
    val lastModified: OffsetDateTime? = null,
    val deaths: Int,
    val title: Int? = null
)
