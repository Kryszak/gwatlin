package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.Serializable

/**
 * Shared interface for skill and trait facts
 */
@Serializable
sealed interface Fact {
        val type: String
        val text: String?
        val icon: String?
}
