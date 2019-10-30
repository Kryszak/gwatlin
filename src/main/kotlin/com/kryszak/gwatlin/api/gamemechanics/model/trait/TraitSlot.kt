package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.google.gson.annotations.SerializedName

/**
 * Trait slot values
 */
enum class TraitSlot {
    @SerializedName("Major")
    MAJOR,
    @SerializedName("Minor")
    MINOR
}
