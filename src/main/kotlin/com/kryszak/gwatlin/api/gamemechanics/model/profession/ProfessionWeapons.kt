package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Model of fields representing weapons available to profession
 */
data class ProfessionWeapons(
        @SerializedName(value = "Axe") val axe: Weapon?,
        @SerializedName(value = "Dagger") val dagger: Weapon?,
        @SerializedName(value = "Mace") val mace: Weapon?,
        @SerializedName(value = "Pistol") val pistol: Weapon?,
        @SerializedName(value = "Sword") val sword: Weapon?,
        @SerializedName(value = "Scepter") val scepter: Weapon?,
        @SerializedName(value = "Focus") val focus: Weapon?,
        @SerializedName(value = "Shield") val shield: Weapon?,
        @SerializedName(value = "Torch") val torch: Weapon?,
        @SerializedName(value = "Warhorn") val warhorn: Weapon?,
        @SerializedName(value = "Greatword") val greatsword: Weapon?,
        @SerializedName(value = "Hammer") val hammer: Weapon?,
        @SerializedName(value = "Longbow") val longbow: Weapon?,
        @SerializedName(value = "Rifle") val rifle: Weapon?,
        @SerializedName(value = "Shortbow") val shortbow: Weapon?,
        @SerializedName(value = "Staff") val staff: Weapon?,
        @SerializedName(value = "Speargun") val speargun: Weapon?,
        @SerializedName(value = "Spear") val spear: Weapon?,
        @SerializedName(value = "Trident") val trident: Weapon?
)
