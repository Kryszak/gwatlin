package com.kryszak.gwatlin.api.wvw.model.rank


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for wvw rank object
 */
@Serializable
data class WvwRank(
        val id: Int,
        @SerialName("min_rank") val minRank: Int,
        val title: String
)
