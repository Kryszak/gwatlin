package com.kryszak.gwatlin.api.gamemechanics.model.mount.skin

import com.google.gson.annotations.SerializedName

/**
 * Data model for mount skin object
 */
data class MountSkin(
        val id: Int,
        val name: String,
        val icon: String,
        val mount: String,
        @SerializedName("dye_slots") val dyeSlots: List<DyeSlot>
)