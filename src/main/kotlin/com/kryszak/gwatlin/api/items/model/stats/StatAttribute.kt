package com.kryszak.gwatlin.api.items.model.stats

/**
 * Data model for statistic attribute property
 */
data class StatAttribute(
        val attribute: String,
        val multiplier: Double,
        val value: Int
)
