package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Item rarity values
 */
@Serializable
enum class ItemRarity {
    @SerialName("Junk")
    JUNK,
    @SerialName("Basic")
    BASIC,
    @SerialName("Fine")
    FINE,
    @SerialName("Masterwork")
    MASTERWORK,
    @SerialName("Rare")
    RARE,
    @SerialName("Exotic")
    EXOTIC,
    @SerialName("Ascended")
    ASCENDED,
    @SerialName("Legendary")
    LEGENDARY
}
