package com.kryszak.gwatlin.api.characters.model.character.equipment

import com.google.gson.annotations.SerializedName

enum class EquipmentItemSlot {
    @SerializedName("HelmAquatic")
    HELM_AQUATIC,
    @SerializedName("Backpack")
    BACKPACK,
    @SerializedName("Coat")
    COAT,
    @SerializedName("Boots")
    BOOTS,
    @SerializedName("Gloves")
    GLOVES,
    @SerializedName("Helm")
    HELM,
    @SerializedName("Leggings")
    LEGGINGS,
    @SerializedName("Accessory1")
    ACCESSORY_1,
    @SerializedName("Accessory2")
    ACCESSORY_2,
    @SerializedName("Ring1")
    RING_1,
    @SerializedName("Ring2")
    RING_2,
    @SerializedName("Amulet")
    AMULET,
    @SerializedName("WeaponAquaticA")
    WEAPON_AQUATIC_A,
    @SerializedName("WeaponAquaticB")
    WEAPON_AQUATIC_B,
    @SerializedName("WeaponA1")
    WEAPON_A_1,
    @SerializedName("WeaponA2")
    WEAPON_A_2,
    @SerializedName("WeaponB1")
    WEAPON_B_1,
    @SerializedName("WeaponB2")
    WEAPON_B_2,
    @SerializedName("Shoulders")
    SHOULDERS,
    @SerializedName("Sickle")
    SICKLE,
    @SerializedName("Axe")
    AXE,
    @SerializedName("Pick")
    PICK
}