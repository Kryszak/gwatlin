package io.github.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.Serializable

/**
 * Data model for icon assets
 */
@Serializable
data class Icon(
        val id: String,
        val icon: String
)
