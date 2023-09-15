package com.kryszak.gwatlin.api.gamemechanics.model.trait

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Trait slot values
 */
@Serializable
enum class TraitSlot {
    @SerialName("Major")
    MAJOR,
    @SerialName("Minor")
    MINOR
}
