package com.kryszak.gwatlin.api.wvw.model.ability

/**
 * Data model for wvw ability object
 */
data class WvwAbility(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val ranks: List<WvwAbilityRank>
)