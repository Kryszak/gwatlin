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
    @SerialName("JadeTechModule")
    JADE_TECH_MODULE,
    @SerialName("Key")
    KEY,
    @SerialName("MiniPet")
    MINI_PET,
    @SerialName("PowerCore")
    POWER_CORE,
    @SerialName("Tool")
    TOOL,
    @SerialName("Trait")
    TRAIT,
    @SerialName("Trinket")
    TRINKET,
    @SerialName("Trophy")
    TROPHY,
    @SerialName("Relic")
    RELIC,
    @SerialName("UpgradeComponent")
    UPGRADE_COMPONENT,
    @SerialName("Weapon")
    WEAPON;

    companion object {
        private val bySerialName = entries.associateBy {
            it.javaClass.getField(it.name).getAnnotation(SerialName::class.java).value
        }

        internal fun fromSerialString(s: String): ItemType = bySerialName.getValue(s)
    }
}
