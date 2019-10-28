package com.kryszak.gwatlin.api.gamemechanics.model.mount.type

import com.google.gson.annotations.SerializedName

/**
 * Data model for mount type object
 */
data class MountType(
        val id: String,
        val name: String,
        @SerializedName("default_skin") val defaultSkin: Int,
        val skins: List<Int>,
        val skills: List<MountSkill>
)