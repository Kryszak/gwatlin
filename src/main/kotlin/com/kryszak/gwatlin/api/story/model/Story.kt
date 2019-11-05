package com.kryszak.gwatlin.api.story.model

/**
 * Data model for story object
 */
data class Story(
        val id: Int,
        val season: String,
        val name: String,
        val description: String,
        val timeline: String,
        val level: Int,
        val order: Int,
        val chapters: List<StoryChapter>,
        val races: List<String>?,
        val professions: List<String>?
)
