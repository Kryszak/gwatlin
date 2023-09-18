package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for currency amount stored in wallet
 */
@Serializable
data class CurrencyAmount(
        val id: Int,
        val value: Int
)
