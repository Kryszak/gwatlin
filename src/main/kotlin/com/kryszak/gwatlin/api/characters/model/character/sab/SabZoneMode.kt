package com.kryszak.gwatlin.api.characters.model.character.sab

import com.google.gson.annotations.SerializedName

/**
 * Enum for SAB zone modes
 */
enum class SabZoneMode {
    @SerializedName("infantile")
    INFANTILE,
    @SerializedName("normal")
    NORMAL,
    @SerializedName("tribulation")
    TRIBULATION
}