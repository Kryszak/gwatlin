package io.github.kryszak.gwatlin.api.guild.model.emblem

import kotlinx.serialization.Serializable

/**
 * Data model for backgrounds object
 */
@Serializable
data class Layer(
        val id: Int,
        val layers: List<String>
)
