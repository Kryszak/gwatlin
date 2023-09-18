package io.github.kryszak.gwatlin.api.gamemechanics.model.trait

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Trait tier values
 */
@Serializable
enum class TraitTier {
    @SerialName("1")
    ADEPT,
    @SerialName("2")
    MASTER,
    @SerialName("3")
    GRANDMASTER
}
