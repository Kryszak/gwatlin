package com.kryszak.gwatlin.api.gamemechanics.model.mount.type

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mount type object
 */
@Serializable
data class MountType(
        val id: String,
        val name: String,
        @SerialName("default_skin") val defaultSkin: Int,
        val skins: List<Int>,
        val skills: List<MountSkill>
)