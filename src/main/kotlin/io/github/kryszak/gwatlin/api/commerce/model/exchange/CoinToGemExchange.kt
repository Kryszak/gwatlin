package io.github.kryszak.gwatlin.api.commerce.model.exchange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for coin to gem exchange object
 */
@Serializable
data class CoinToGemExchange(
        @SerialName("coins_per_gem") val coinsPerGem: Int,
        val quantity: Int
)