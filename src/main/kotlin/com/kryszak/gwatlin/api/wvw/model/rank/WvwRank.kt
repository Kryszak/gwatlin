package com.kryszak.gwatlin.api.wvw.model.rank


import com.google.gson.annotations.SerializedName

/**
 * Data model for wvw rank object
 */
data class WvwRank(
        val id: Int,
        @SerializedName("min_rank") val minRank: Int,
        val title: String
)
