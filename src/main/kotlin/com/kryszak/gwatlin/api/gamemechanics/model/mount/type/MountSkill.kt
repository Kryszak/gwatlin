package com.kryszak.gwatlin.api.gamemechanics.model.mount.type

import kotlinx.serialization.Serializable

/**
 * Data model for mount type skill field
 */
@Serializable
data class MountSkill(
        val id: Int,
        val slot: String
)