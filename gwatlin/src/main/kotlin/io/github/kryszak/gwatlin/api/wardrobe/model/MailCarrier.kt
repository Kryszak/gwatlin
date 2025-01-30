package io.github.kryszak.gwatlin.api.wardrobe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mail carrier endpoint response
 */
@Serializable
data class MailCarrier(
    val id: Int,
    @SerialName("unlock_items")
    val unlockItems: List<Int> = listOf(),
    val order: Int,
    val icon: String,
    val name: String,
    val flags: List<String> = listOf(),
)
