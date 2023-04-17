package com.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for title object
 */
@Serializable
data class Title(
        val id: Int,
        val name: String,
        val achievement: Int? = null,
        val achievements: List<Int> = listOf(),
        @SerialName("ap_required") val apRequired: Int? = null
)
