package io.github.kryszak.gwatlin.api.wardrobe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for glider endpoint response
 */
@Serializable
data class Glider(
    val id: Int,
    @SerialName("unlock_items")
    val unlockItems: List<Long>? = listOf(),
    val order: Int,
    val icon: String,
    val name: String,
    val description: String,
    @SerialName("default_dyes")
    val defaultDyes: List<Int>,
)
