package com.kryszak.gwatlin.api.account.model.mastery

/**
 * Data model for mastery points by region
 */
data class MasteryPoints(
        val region: String,
        val spent: Int,
        val earned: Int
)
