package io.github.kryszak.gwatlin.api.gamemechanics.model.mount.type

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
        val skins: List<Int> = listOf(),
        val skills: List<MountSkill> = listOf(),
)