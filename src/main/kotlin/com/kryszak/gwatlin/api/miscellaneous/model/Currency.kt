package com.kryszak.gwatlin.api.miscellaneous.model

import kotlinx.serialization.Serializable

/**
 * Data model for currency object
 */
@Serializable
data class Currency(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val order: Int
)
