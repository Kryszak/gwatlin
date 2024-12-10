package io.github.kryszak.gwatlin.api.story.model

import kotlinx.serialization.Serializable

/**
 * Data model for story object
 */
@Serializable
data class Story(
        val id: Int,
        val season: String,
        val name: String,
        val description: String,
        val timeline: String,
        val level: Int,
        val order: Int,
        val chapters: List<StoryChapter> = listOf(),
        val races: List<String> = listOf(),
        val professions: List<String> = listOf(),
        val flags: List<String> = listOf()
)
