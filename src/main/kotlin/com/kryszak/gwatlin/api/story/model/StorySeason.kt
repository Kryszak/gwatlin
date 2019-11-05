package com.kryszak.gwatlin.api.story.model

/**
 * Data model for story season object
 */
data class StorySeason(
        val id: String,
        val name: String,
        val order: Int,
        val stories: List<Int>
)
