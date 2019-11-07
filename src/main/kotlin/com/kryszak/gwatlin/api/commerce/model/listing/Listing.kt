package com.kryszak.gwatlin.api.commerce.model.listing

import com.google.gson.annotations.SerializedName

/**
 * Data model for buys/sells object in commerce listing
 */
data class Listing(
        val listings: Int,
        @SerializedName("unit_price") val unitPrice: Int,
        val quantity: Int
)
