package com.kryszak.gwatlin.api.gamemechanics.model.trait

import com.google.gson.annotations.SerializedName

/**
 * Trait tier values
 */
enum class TraitTier {
    @SerializedName("1")
    ADEPT,
    @SerializedName("2")
    MASTER,
    @SerializedName("3")
    GRANDMASTER
}
