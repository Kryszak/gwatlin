package com.kryszak.gwatlin.api.commerce.model.price

/**
 * Data model for commerce price object
 */
data class Price(
        val id: Int,
        val whitelisted: Boolean,
        val buys: BuySellObject,
        val sells: BuySellObject
)