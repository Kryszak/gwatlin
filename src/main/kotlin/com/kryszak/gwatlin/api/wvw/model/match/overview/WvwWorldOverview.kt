package com.kryszak.gwatlin.api.wvw.model.match.overview

import com.google.gson.annotations.SerializedName
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchAllWorlds
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount

/**
 * Data model for overview sub-endpoint
 */
data class WvwWorldOverview(
        @SerializedName("all_worlds") val allWorlds: WvwMatchAllWorlds,
        @SerializedName("end_time") val endTime: String,
        val id: String,
        @SerializedName("start_time") val startTime: String,
        val worlds: WvwMatchWorldCount
)
