package com.kryszak.gwatlin.api.items.model.item

import com.google.gson.annotations.SerializedName

/**
 * Item type values
 */
enum class ItemType {
    @SerializedName("Armor")
    ARMOR,
    @SerializedName("Back")
    BACK,
    @SerializedName("Bag")
    BAG,
    @SerializedName("Consumable")
    CONSUMABLE,
    @SerializedName("Container")
    CONTAINER,
    @SerializedName("CraftingMaterial")
    CRAFTING_MATERIAL,
    @SerializedName("Gathering")
    GATHERING,
    @SerializedName("Gizmo")
    GIZMO,
    @SerializedName("Key")
    KEY,
    @SerializedName("MiniPet")
    MINI_PET,
    @SerializedName("Tool")
    TOOL,
    @SerializedName("Trait")
    TRAIT,
    @SerializedName("Trinket")
    TRINKET,
    @SerializedName("Trophy")
    TROPHY,
    @SerializedName("UpgradeComponent")
    UPGRADE_COMPONENT,
    @SerializedName("Weapon")
    WEAPON
}
