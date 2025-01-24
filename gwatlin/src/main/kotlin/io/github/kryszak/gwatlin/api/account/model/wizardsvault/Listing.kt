package io.github.kryszak.gwatlin.api.account.model.wizardsvault

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
    val purchased: Long? = null,
    @SerialName("purchase_limit")
    val purchaseLimit: Long? = null,
)

/**
 * Data model for wizard's vault listing type field
 */
enum class ListingType {
    @SerialName("Featured")
    FEATURED,

    @SerialName("Normal")
    NORMAL,

    @SerialName("Legacy")
    LEGACY
}
