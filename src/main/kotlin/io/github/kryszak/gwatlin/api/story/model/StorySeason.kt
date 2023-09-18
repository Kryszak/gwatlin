package io.github.kryszak.gwatlin.api.story.model

import kotlinx.serialization.Serializable

/**
 * Data model for story season object
 */
@Serializable
data class StorySeason(
        val id: String,
        val name: String,
        val order: Int,
        val stories: List<Int>
)
