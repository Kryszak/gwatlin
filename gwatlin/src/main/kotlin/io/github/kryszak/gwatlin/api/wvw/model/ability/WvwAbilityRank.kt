package io.github.kryszak.gwatlin.api.wvw.model.ability

import kotlinx.serialization.Serializable

/**
 * Data model for wvw ability rank property
 */
@Serializable
data class WvwAbilityRank(
        val cost: Int,
        val effect: String
)
