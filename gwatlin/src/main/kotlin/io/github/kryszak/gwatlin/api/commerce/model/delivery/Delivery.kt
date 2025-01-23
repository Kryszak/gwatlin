package io.github.kryszak.gwatlin.api.commerce.model.delivery

import kotlinx.serialization.Serializable

/**
 * Data model for pending account delivery
 */
@Serializable
data class Delivery(
    val coins: Long,
    val items: List<DeliveryItem> = listOf(),
)

