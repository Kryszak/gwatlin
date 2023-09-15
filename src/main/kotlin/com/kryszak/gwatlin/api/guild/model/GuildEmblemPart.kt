package com.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.Serializable

/**
 * Data model for foreground color
 */
@Serializable
data class GuildEmblemPart(
        val id: Int,
        val colors: List<Int>
)
