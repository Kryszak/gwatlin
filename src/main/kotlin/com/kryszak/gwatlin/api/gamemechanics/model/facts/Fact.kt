package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.Serializable

/**
 * Data model for trait fact property
 */
@Serializable
sealed interface Fact {
        val type: String
        val text: String?
        val icon: String?
}
