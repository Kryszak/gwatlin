package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.Serializable

/**
 * Shared interface for skill and trait facts
 */
@Serializable
sealed interface Fact {
    val text: String?
    val icon: String?

    val type: String
}
