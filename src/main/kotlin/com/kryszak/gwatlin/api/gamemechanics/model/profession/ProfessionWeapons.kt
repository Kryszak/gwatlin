package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Model of fields representing weapons available to profession
 */
@Serializable
data class ProfessionWeapons(
        @SerialName("Axe") val axe: Weapon?,
        @SerialName("Dagger") val dagger: Weapon?,
        @SerialName("Mace") val mace: Weapon?,
        @SerialName("Pistol") val pistol: Weapon?,
        @SerialName("Sword") val sword: Weapon?,
        @SerialName("Scepter") val scepter: Weapon?,
        @SerialName("Focus") val focus: Weapon?,
        @SerialName("Shield") val shield: Weapon?,
        @SerialName("Torch") val torch: Weapon?,
        @SerialName("Warhorn") val warhorn: Weapon?,
        @SerialName("Greatword") val greatsword: Weapon?,
        @SerialName("Hammer") val hammer: Weapon?,
        @SerialName("Longbow") val longbow: Weapon?,
        @SerialName("Rifle") val rifle: Weapon?,
        @SerialName("Shortbow") val shortbow: Weapon?,
        @SerialName("Staff") val staff: Weapon?,
        @SerialName("Speargun") val speargun: Weapon?,
        @SerialName("Spear") val spear: Weapon?,
        @SerialName("Trident") val trident: Weapon?
)
