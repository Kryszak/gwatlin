package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Model of fields representing weapons available to profession
 */
data class ProfessionWeapons(
        @SerializedName("Axe") val axe: Weapon?,
        @SerializedName("Dagger") val dagger: Weapon?,
        @SerializedName("Mace") val mace: Weapon?,
        @SerializedName("Pistol") val pistol: Weapon?,
        @SerializedName("Sword") val sword: Weapon?,
        @SerializedName("Scepter") val scepter: Weapon?,
        @SerializedName("Focus") val focus: Weapon?,
        @SerializedName("Shield") val shield: Weapon?,
        @SerializedName("Torch") val torch: Weapon?,
        @SerializedName("Warhorn") val warhorn: Weapon?,
        @SerializedName("Greatword") val greatsword: Weapon?,
        @SerializedName("Hammer") val hammer: Weapon?,
        @SerializedName("Longbow") val longbow: Weapon?,
        @SerializedName("Rifle") val rifle: Weapon?,
        @SerializedName("Shortbow") val shortbow: Weapon?,
        @SerializedName("Staff") val staff: Weapon?,
        @SerializedName("Speargun") val speargun: Weapon?,
        @SerializedName("Spear") val spear: Weapon?,
        @SerializedName("Trident") val trident: Weapon?
)
