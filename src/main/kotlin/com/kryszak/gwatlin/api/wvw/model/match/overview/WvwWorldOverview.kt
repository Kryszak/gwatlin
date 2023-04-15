package com.kryszak.gwatlin.api.wvw.model.match.overview

import kotlinx.serialization.SerialName
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchAllWorlds
import com.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount
import kotlinx.serialization.Serializable

/**
 * Data model for overview sub-endpoint
 */
@Serializable
data class WvwWorldOverview(
        @SerialName("all_worlds") val allWorlds: WvwMatchAllWorlds,
        @SerialName("end_time") val endTime: String,
        val id: String,
        @SerialName("start_time") val startTime: String,
        val worlds: WvwMatchWorldCount
)
