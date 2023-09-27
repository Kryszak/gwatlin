package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Root data model for item class
 */
@Serializable
sealed class Item {
    abstract val id: Int
    abstract val chatLink: String
    abstract val name: String
    abstract val icon: String
    abstract val description: String
    abstract val type: ItemType
    abstract val rarity: ItemRarity
    abstract val level: Int
    abstract val vendorValue: Int
    abstract val defaultSkin: Int?
    abstract val flags: List<String>
    abstract val gameTypes: List<String>
    abstract val restrictions: List<String>
    abstract val details: ItemDetails?
}

/**
 * Data model for armor item
 */
@Serializable
@SerialName("Armor")
data class ArmorItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ArmorDetails? = null
) : Item()

/**
 * Data model for back item
 */
@Serializable
@SerialName("Back")
data class BackItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: BackItemDetails? = null
) : Item()

/**
 * Data model for Bag item
 */
@Serializable
@SerialName("Bag")
data class BagItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: BagItemDetails? = null
) : Item()

/**
 * Data model for consumable item
 */
@Serializable
@SerialName("Consumable")
data class ConsumableItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String = "",
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ConsumableDetails? = null
) : Item()

/**
 * Data model for container item
 */
@Serializable
@SerialName("Container")
data class ContainerItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ContainerDetails? = null
) : Item()

/**
 * Data model for crafting material item
 */
@Serializable
@SerialName("CraftingMaterial")
data class CraftingMaterialItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for gathering item
 */
@Serializable
@SerialName("Gathering")
data class GatheringItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: GatheringDetails? = null
) : Item()

/**
 * Data model for gizmo item
 */
@Serializable
@SerialName("Gizmo")
data class GizmoItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: GizmoDetails? = null
) : Item()

/**
 * Data model for jade tech item
 */
@Serializable
@SerialName("JadeTechModule")
data class JadeTechModuleItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for key item
 */
@Serializable
@SerialName("Key")
data class KeyItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for miniature item
 */
@Serializable
@SerialName("MiniPet")
data class MiniatureItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: MiniatureDetails? = null
) : Item()

/**
 * Data model for power core item
 */
@Serializable
@SerialName("PowerCore")
data class PowerCoreItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for salvage kit item
 */
@Serializable
@SerialName("Tool")
data class SalvageKitItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: SalvageKitDetails? = null
) : Item()

/**
 * Data model for trait item
 */
@Serializable
@SerialName("Trait")
data class TraitItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for trinket item
 */
@Serializable
@SerialName("Trinket")
data class TrinketItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: TrinketDetails? = null
) : Item()

/**
 * Data model for trophy item
 */
@Serializable
@SerialName("Trophy")
data class TrophyItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: ItemDetails? = null
) : Item()

/**
 * Data model for upgrade component item
 */
@Serializable
@SerialName("UpgradeComponent")
data class UpgradeComponentItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: UpgradeDetails? = null
) : Item()

/**
 * Data model for weapon item
 */
@Serializable
@SerialName("Weapon")
data class WeaponItem(
    override val id: Int,
    @SerialName("chat_link") override val chatLink: String,
    override val name: String,
    override val icon: String,
    override val description: String,
    override val type: ItemType,
    override val rarity: ItemRarity,
    override val level: Int,
    @SerialName("vendor_value") override val vendorValue: Int,
    @SerialName("default_skin") override val defaultSkin: Int? = null,
    override val flags: List<String> = listOf(),
    @SerialName("game_types") override val gameTypes: List<String> = listOf(),
    override val restrictions: List<String> = listOf(),
    override val details: WeaponDetails? = null
) : Item()