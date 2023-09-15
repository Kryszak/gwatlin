package com.kryszak.gwatlin.api.commerce.model.listing

import kotlinx.serialization.Serializable

/**
 * Data model for commerce listing object
 */
@Serializable
data class CommerceListing(
        val id: Int,
        val buys: List<Listing>,
        val sells: List<Listing>
)