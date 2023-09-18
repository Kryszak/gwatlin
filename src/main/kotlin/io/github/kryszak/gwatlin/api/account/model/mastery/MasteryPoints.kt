package io.github.kryszak.gwatlin.api.account.model.mastery

import kotlinx.serialization.Serializable

/**
 * Data model for mastery points by region
 */
@Serializable
data class MasteryPoints(
        val region: String,
        val spent: Int,
        val earned: Int
)
