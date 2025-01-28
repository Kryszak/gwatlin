package io.github.kryszak.gwatlin.api.homestead.model

import kotlinx.serialization.Serializable

/**
 * Data model for decoration category response
 */
@Serializable
data class Category(
    val id: Int,
    val name: String,
)
