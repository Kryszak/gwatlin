package com.kryszak.gwatlin.api.guild.model

/**
 * Data model for embled guild property
 */
data class GuildEmblem(
        val background: GuildEmblemPart,
        val foreground: GuildEmblemPart,
        val flags: List<String>
)
