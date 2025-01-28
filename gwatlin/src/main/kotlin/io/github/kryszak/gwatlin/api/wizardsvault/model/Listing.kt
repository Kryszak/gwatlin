package io.github.kryszak.gwatlin.api.wizardsvault.model

import io.github.kryszak.gwatlin.api.shared.ListingType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wizard's vault listing
 */
@Serializable
data class Listing(
    val id: Int,
    @SerialName("item_id")
    val itemId: Int,
    @SerialName("item_count")
    val itemCount: Long,
    val type: ListingType,
    val cost: Long,
)
