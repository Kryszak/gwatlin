package com.kryszak.gwatlin.api.story.model

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
        val races: List<String>?,
        val professions: List<String>?
)
