package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Item type values
 */
@Serializable
enum class ItemType {
    @SerialName("Armor")
    ARMOR,
    @SerialName("Back")
    BACK,
    @SerialName("Bag")
    BAG,
    @SerialName("Consumable")
    CONSUMABLE,
    @SerialName("Container")
    CONTAINER,
    @SerialName("CraftingMaterial")
    CRAFTING_MATERIAL,
    @SerialName("Gathering")
    GATHERING,
    @SerialName("Gizmo")
    GIZMO,
    @SerialName("Key")
    KEY,
    @SerialName("MiniPet")
    MINI_PET,
    @SerialName("Tool")
    TOOL,
    @SerialName("Trait")
    TRAIT,
    @SerialName("Trinket")
    TRINKET,
    @SerialName("Trophy")
    TROPHY,
    @SerialName("UpgradeComponent")
    UPGRADE_COMPONENT,
    @SerialName("Weapon")
    WEAPON
}
