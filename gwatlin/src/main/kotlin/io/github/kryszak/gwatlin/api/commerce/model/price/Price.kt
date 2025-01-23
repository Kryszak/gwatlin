package io.github.kryszak.gwatlin.api.commerce.model.price

import kotlinx.serialization.Serializable

/**
 * Data model for commerce price object
 */
@Serializable
data class Price(
        val id: Int,
        val whitelisted: Boolean,
        val buys: BuySellObject,
        val sells: BuySellObject
)