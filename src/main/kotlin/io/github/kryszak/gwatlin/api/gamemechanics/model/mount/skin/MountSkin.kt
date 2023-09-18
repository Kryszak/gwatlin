package io.github.kryszak.gwatlin.api.gamemechanics.model.mount.skin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mount skin object
 */
@Serializable
data class MountSkin(
        val id: Int,
        val name: String,
        val icon: String,
        val mount: String,
        @SerialName("dye_slots") val dyeSlots: List<DyeSlot>
)