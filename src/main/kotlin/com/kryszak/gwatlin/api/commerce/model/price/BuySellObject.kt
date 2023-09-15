package com.kryszak.gwatlin.api.commerce.model.price

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for buy/sell property of price objeect
 */
@Serializable
data class BuySellObject(
        val quantity: Int,
        @SerialName("unit_price") val unitPrice: Int
)
