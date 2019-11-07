package com.kryszak.gwatlin.api.commerce.model.price

import com.google.gson.annotations.SerializedName

/**
 * Data model for buy/sell property of price objeect
 */
data class BuySellObject(
        val quantity: Int,
        @SerializedName("unit_price") val unitPrice: Int
)
