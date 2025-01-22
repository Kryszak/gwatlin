package io.github.kryszak.gwatlin.api.characters.model.character.sab

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum for SAB zone modes
 */
@Serializable
enum class SabZoneMode {
    @SerialName("infantile")
    INFANTILE,
    @SerialName("normal")
    NORMAL,
    @SerialName("tribulation")
    TRIBULATION
}