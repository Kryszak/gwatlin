package io.github.kryszak.gwatlin.api.commerce.model.listing

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for buys/sells object in commerce listing
 */
@Serializable
data class Listing(
        val listings: Int,
        @SerialName("unit_price") val unitPrice: Int,
        val quantity: Int
)
