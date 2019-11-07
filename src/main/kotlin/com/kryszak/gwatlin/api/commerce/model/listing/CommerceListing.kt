package com.kryszak.gwatlin.api.commerce.model.listing

/**
 * Data model for commerce listing object
 */
data class CommerceListing(
        val id: Int,
        val buys: List<Listing>,
        val sells: List<Listing>
)