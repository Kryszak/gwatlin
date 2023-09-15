package com.kryszak.gwatlin.api.guild.model

import kotlinx.serialization.Serializable

/**
 * Data model for embled guild property
 */
@Serializable
data class GuildEmblem(
        val background: GuildEmblemPart,
        val foreground: GuildEmblemPart,
        val flags: List<String>
)
