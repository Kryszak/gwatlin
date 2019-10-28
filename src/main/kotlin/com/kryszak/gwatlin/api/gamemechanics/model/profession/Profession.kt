package com.kryszak.gwatlin.api.gamemechanics.model.profession

import com.google.gson.annotations.SerializedName

/**
 * Data model for profession object
 */
data class Profession(
        val id: String,
        val name: String,
        val icon: String,
        @SerializedName(value = "icon_big") val iconBig: String,
        val specializations: List<Int>,
        val training: List<Training>,
        val weapons: ProfessionWeapons
)
