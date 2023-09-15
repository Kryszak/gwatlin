package com.kryszak.gwatlin.api.wvw.model.match

import kotlinx.serialization.Serializable

/**
 * Data model for wvw match world points count
 */
@Serializable
data class WvwMatchWorldCount(
        val red: Int,
        val blue: Int,
        val green: Int
)
