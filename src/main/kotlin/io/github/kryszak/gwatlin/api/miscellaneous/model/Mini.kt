package io.github.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for mini object
 */
@Serializable
data class Mini(
        val id: Int,
        val name: String,
        val icon: String,
        val order: Int,
        @SerialName("item_id") val itemId: Int,
        val unlock: String? = null
)
