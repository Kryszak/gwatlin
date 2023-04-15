package com.kryszak.gwatlin.api.items.model.stats

import kotlinx.serialization.Serializable

/**
 * Data model for statistic attribute property
 */
@Serializable
data class StatAttribute(
        val attribute: String,
        val multiplier: Double,
        val value: Int
)
