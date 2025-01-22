package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName

/**
 * Possible values for combo field types
 */
enum class ComboFieldType {
    @SerialName("Air")
    AIR,
    @SerialName("Dark")
    DARK,
    @SerialName("Fire")
    FIRE,
    @SerialName("Ice")
    ICE,
    @SerialName("Light")
    LIGHT,
    @SerialName("Lightning")
    LIGHTNING,
    @SerialName("Poison")
    POISON,
    @SerialName("Smoke")
    SMOKE,
    @SerialName("Ethereal")
    ETHEREAL,
    @SerialName("Water")
    WATER
}