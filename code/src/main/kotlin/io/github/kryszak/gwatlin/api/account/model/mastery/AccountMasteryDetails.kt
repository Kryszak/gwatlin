package io.github.kryszak.gwatlin.api.account.model.mastery

import kotlinx.serialization.Serializable

/**
 * Data model for /account/mastery/points endpoint
 */
@Serializable
data class AccountMasteryDetails(
        val totals: List<MasteryPoints> = listOf(),
        val unlocked: List<Int> = listOf()
)
