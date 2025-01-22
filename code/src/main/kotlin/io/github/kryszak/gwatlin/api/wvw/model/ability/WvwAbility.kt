package io.github.kryszak.gwatlin.api.wvw.model.ability

import kotlinx.serialization.Serializable

/**
 * Data model for wvw ability object
 */
@Serializable
data class WvwAbility(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val ranks: List<WvwAbilityRank> = listOf(),
)