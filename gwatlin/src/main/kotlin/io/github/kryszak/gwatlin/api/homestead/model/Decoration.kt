package io.github.kryszak.gwatlin.api.homestead.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for homestead decoration object.
 */
@Serializable
data class Decoration(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("max_count")
    val maxCount: Int,
    val icon: String,
    val categories: List<Int> = listOf(),
)
