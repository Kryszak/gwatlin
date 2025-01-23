package io.github.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match map bonus property
 */
@Serializable
data class WvwMatchMapBonus(
        val type: String,
        val owner: String
)
