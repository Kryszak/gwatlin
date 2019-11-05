package com.kryszak.gwatlin.api.miscellaneous.model

/**
 * Data model for currency object
 */
data class Currency(
        val id: Int,
        val name: String,
        val description: String,
        val icon: String,
        val order: Int
)
