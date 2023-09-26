package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Root data model for item details
 */
@Serializable
sealed class ItemDetails

/**
 * Data model for armor details
 */
@Serializable
data class ArmorDetails(
    val type: String,
    @SerialName("weight_class") val weightClass: String,
    val defense: Int,
    @SerialName("infusion_slots") val infusionSlots: List<InfusionSlot>,
    @SerialName("attribute_adjustment") val attributeAdjustment: Int,
    @SerialName("infix_upgrade") val infixUpgrade: InfixUpgrade?,
    @SerialName("suffix_item_id") val suffixItemId: Int?,
    @SerialName("secondary_suffix_item_id") val secondarySuffixItemId: String,
    @SerialName("stat_choices") val statChoices: List<Int>
) : ItemDetails()

/**
 * Data model for back item details
 */
@Serializable
data class BackItemDetails(
    @SerialName("infusion_slots") val infusionSlots: List<InfusionSlot>,
    @SerialName("attribute_adjustment") val attributeAdjustment: Double,
    @SerialName("infix_upgrade") val infixUpgrade: InfixUpgrade?,
    @SerialName("suffix_item_id") val suffixItemId: Int?,
    @SerialName("secondary_suffix_item_id") val secondarySuffixItemId: String,
    @SerialName("stat_choices") val statChoices: List<Int>
) : ItemDetails()

/**
 * Data model for bag item details
 */
@Serializable
data class BagItemDetails(
    val size: Int,
    @SerialName("no_sell_or_sort") val noSellOrSort: Boolean
) : ItemDetails()

/**
 * Data model for consumable details
 */
@Serializable
data class ConsumableDetails(
    val type: String,
    val description: String?,
    @SerialName("duration_ms") val durationMs: String?,
    @SerialName("unlock_type") val unlockType: String?,
    @SerialName("color_id") val colorId: Int?,
    @SerialName("recipe_id") val recipeId: Int?,
    @SerialName("extra_recipe_ids") val extraRecipeIds: List<Int>,
    @SerialName("guild_upgrade_id") val guildUpgradeId: Int?,
    @SerialName("apply_count") val applyCount: Int?,
    val name: String?,
    val icon: String?,
    val skins: List<Int>
) : ItemDetails()

/**
 * Data model for container details
 */
@Serializable
data class ContainerDetails(
    val type: String
) : ItemDetails()

/**
 * Data model for gathering details
 */
@Serializable
data class GatheringDetails(
    val type: String
) : ItemDetails()

/**
 * Data model for gizmo details
 */
@Serializable
data class GizmoDetails(
    val type: String,
    @SerialName("guild_upgrade_id") val guildUpgradeId: Int?,
    @SerialName("vendor_ids") val vendorIds: List<Int>
) : ItemDetails()

/**
 * Data model for miniature details
 */
@Serializable
data class MiniatureDetails(
    @SerialName("minipet_id") val minipetId: Int
) : ItemDetails()

/**
 * Data model for salvage kit details
 */
@Serializable
data class SalvageKitDetails(
    val type: String,
    val charges: Int
) : ItemDetails()

/**
 * Data model for trinket details
 */
@Serializable
data class TrinketDetails(
    val type: String,
    @SerialName("infusion_slots") val infusionSlots: List<InfusionSlot>,
    @SerialName("attribute_adjustment") val attributeAdjustment: Int,
    @SerialName("infix_upgrade") val infixUpgrade: InfixUpgrade?,
    @SerialName("suffix_item_id") val suffixItemId: Int?,
    @SerialName("secondary_suffix_item_id") val secondarySuffixItemId: String,
    @SerialName("stat_choices") val statChoices: List<Int>
) : ItemDetails()

/**
 * Data model for upgrade details
 */
@Serializable
data class UpgradeDetails(
    val type: String,
    val flags: List<String>,
    @SerialName("infusion_upgrade_flags") val infusionUpgradeFlags: List<String>,
    val suffix: String,
    @SerialName("infix_upgrade") val infixUpgrade: InfixUpgrade?,
    val bonuses: List<String>
) : ItemDetails()

/**
 * Data model for weapon details
 */
@Serializable
data class WeaponDetails(
    val type: String,
    @SerialName("damage_type") val damageType: String,
    @SerialName("min_power") val minPower: Int,
    @SerialName("max_power") val maxPower: Int,
    val defense: Double,
    @SerialName("infusion_slots") val infusionSlots: List<InfusionSlot>,
    @SerialName("attribute_adjustment") val attributeAdjustment: Int? = null,
    @SerialName("infix_upgrade") val infixUpgrade: InfixUpgrade?,
    @SerialName("suffix_item_id") val suffixItemId: Int?,
    @SerialName("secondary_suffix_item_id") val secondarySuffixItemId: String,
    @SerialName("stat_choices") val statChoices: List<Int> = listOf()
) : ItemDetails()