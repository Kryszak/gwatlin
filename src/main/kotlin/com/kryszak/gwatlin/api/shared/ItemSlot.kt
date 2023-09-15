package com.kryszak.gwatlin.api.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum for possible item slot values
 */
@Serializable
enum class ItemSlot {
    @SerialName("HelmAquatic")
    HELM_AQUATIC,
    @SerialName("Backpack")
    BACKPACK,
    @SerialName("Coat")
    COAT,
    @SerialName("Boots")
    BOOTS,
    @SerialName("Gloves")
    GLOVES,
    @SerialName("Helm")
    HELM,
    @SerialName("Leggings")
    LEGGINGS,
    @SerialName("Accessory1")
    ACCESSORY_1,
    @SerialName("Accessory2")
    ACCESSORY_2,
    @SerialName("Ring1")
    RING_1,
    @SerialName("Ring2")
    RING_2,
    @SerialName("Amulet")
    AMULET,
    @SerialName("WeaponAquaticA")
    WEAPON_AQUATIC_A,
    @SerialName("WeaponAquaticB")
    WEAPON_AQUATIC_B,
    @SerialName("WeaponA1")
    WEAPON_A_1,
    @SerialName("WeaponA2")
    WEAPON_A_2,
    @SerialName("WeaponB1")
    WEAPON_B_1,
    @SerialName("WeaponB2")
    WEAPON_B_2,
    @SerialName("Shoulders")
    SHOULDERS,
    @SerialName("Sickle")
    SICKLE,
    @SerialName("Axe")
    AXE,
    @SerialName("Pick")
    PICK
}