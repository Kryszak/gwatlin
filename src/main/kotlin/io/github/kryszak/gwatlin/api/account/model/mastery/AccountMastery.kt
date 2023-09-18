package io.github.kryszak.gwatlin.api.account.model.mastery

import kotlinx.serialization.Serializable

/**
 * Data model for account mastery object
 */
@Serializable
data class AccountMastery(
        val id: Int,
        val level: Int
)
