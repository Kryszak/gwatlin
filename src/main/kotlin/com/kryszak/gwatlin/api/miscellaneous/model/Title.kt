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
        val achievement: Int,
        val achievements: List<Int>,
        @SerialName("ap_required") val apRequired: Int?
)
