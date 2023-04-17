package com.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Model of fields representing weapons available to profession
 */
@Serializable
data class ProfessionWeapons(
        @SerialName("Axe") val axe: Weapon? = null,
        @SerialName("Dagger") val dagger: Weapon? = null,
        @SerialName("Mace") val mace: Weapon? = null,
        @SerialName("Pistol") val pistol: Weapon? = null,
        @SerialName("Sword") val sword: Weapon? = null,
        @SerialName("Scepter") val scepter: Weapon? = null,
        @SerialName("Focus") val focus: Weapon? = null,
        @SerialName("Shield") val shield: Weapon? = null,
        @SerialName("Torch") val torch: Weapon? = null,
        @SerialName("Warhorn") val warhorn: Weapon? = null,
        @SerialName("Greatsword") val greatsword: Weapon? = null,
        @SerialName("Hammer") val hammer: Weapon? = null,
        @SerialName("Longbow") val longbow: Weapon? = null,
        @SerialName("Rifle") val rifle: Weapon? = null,
        @SerialName("Shortbow") val shortbow: Weapon? = null,
        @SerialName("Staff") val staff: Weapon? = null,
        @SerialName("Speargun") val speargun: Weapon? = null,
        @SerialName("Spear") val spear: Weapon? = null,
        @SerialName("Trident") val trident: Weapon? = null
)
