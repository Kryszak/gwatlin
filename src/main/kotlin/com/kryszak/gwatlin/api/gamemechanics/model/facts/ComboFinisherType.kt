package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName

/**
 * Possible values for combo finisher types
 */
enum class ComboFinisherType {
    @SerialName("Blast")
    BLAST,
    @SerialName("Leap")
    LEAP,
    @SerialName("Projectile")
    PROJECTILE,
    @SerialName("Whirl")
    WHIRL
}