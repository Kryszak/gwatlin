package io.github.kryszak.gwatlin.api.story.model

import kotlinx.serialization.Serializable

/**
 * Data model for backstory question object
 */
@Serializable
data class BackstoryQuestion(
        val id: Int,
        val title: String,
        val description: String,
        val answers: List<String>,
        val order: Int,
        val races: List<String> = listOf(),
        val professions: List<String> = listOf()
)
