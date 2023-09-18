package io.github.kryszak.gwatlin.api.guild.model.stash

import kotlinx.serialization.Serializable

/**
 * Data model for item stored in guild stash
 */
@Serializable
data class GuildStashItem(
        val id: Int,
        val count: Int
)
