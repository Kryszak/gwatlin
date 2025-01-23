package io.github.kryszak.gwatlin.api.commerce.model.delivery

import kotlinx.serialization.Serializable

/**
 * Data model for pending account delivery item
 */
@Serializable
data class DeliveryItem(
    val id: Int,
    val count: Int,
)