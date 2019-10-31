package com.kryszak.gwatlin.api.items.model.item

import com.google.gson.annotations.SerializedName

/**
 * Item rarity values
 */
enum class ItemRarity {
    @SerializedName("Junk")
    JUNK,
    @SerializedName("Basic")
    BASIC,
    @SerializedName("Fine")
    FINE,
    @SerializedName("Masterwork")
    MASTERWORK,
    @SerializedName("Rare")
    RARE,
    @SerializedName("Exotic")
    EXOTIC,
    @SerializedName("Ascended")
    ASCENDED,
    @SerializedName("Legendary")
    LEGENDARY
}
