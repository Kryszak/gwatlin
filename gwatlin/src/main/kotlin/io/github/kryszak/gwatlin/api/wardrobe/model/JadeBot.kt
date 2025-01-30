package io.github.kryszak.gwatlin.api.wardrobe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for jade bot endpoint response
 */
@Serializable
data class JadeBot(
    val id: Int,
    val name: String,
    val description: String,
    @SerialName("unlock_item")
    val unlockItem: Int
)
