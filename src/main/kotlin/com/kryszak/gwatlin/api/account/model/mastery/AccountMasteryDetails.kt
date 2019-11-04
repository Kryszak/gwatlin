package com.kryszak.gwatlin.api.account.model.mastery

/**
 * Data model for /account/mastery/points endpoint
 */
data class AccountMasteryDetails(
        val totals: List<MasteryPoints>,
        val unlocked: List<Int>
)
