package com.kryszak.gwatlin.api.story.model

import kotlinx.serialization.Serializable

/**
 * Data model for story chapter property
 */
@Serializable
data class StoryChapter(
        val name: String
)
