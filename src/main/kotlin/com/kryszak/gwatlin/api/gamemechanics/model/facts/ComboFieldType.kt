package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName

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