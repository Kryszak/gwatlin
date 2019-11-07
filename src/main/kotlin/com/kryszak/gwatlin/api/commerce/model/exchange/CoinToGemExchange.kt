package com.kryszak.gwatlin.api.commerce.model.exchange

import com.google.gson.annotations.SerializedName

/**
 * Data model for coin to gem exchange object
 */
data class CoinToGemExchange(
        @SerializedName("coins_per_gem") val coinsPerGem: Int,
        val quantity: Int
)