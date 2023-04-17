package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName

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