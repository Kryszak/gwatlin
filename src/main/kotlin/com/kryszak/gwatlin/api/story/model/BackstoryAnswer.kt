package com.kryszak.gwatlin.api.story.model

/**
 * Data model for backstory answer object
 */
data class BackstoryAnswer(
        val id: String,
        val title: String,
        val description: String,
        val journal: String,
        val question: Int,
        val professions: List<String>?,
        val races: List<String>?
)
