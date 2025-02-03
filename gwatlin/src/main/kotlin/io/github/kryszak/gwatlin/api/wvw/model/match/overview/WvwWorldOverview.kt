package io.github.kryszak.gwatlin.api.wvw.model.match.overview

import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchAllWorlds
import io.github.kryszak.gwatlin.api.wvw.model.match.WvwMatchWorldCount
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

/**
 * Data model for overview sub-endpoint
 */
@Serializable
data class WvwWorldOverview(
        @SerialName("all_worlds") val allWorlds: WvwMatchAllWorlds,
        @Contextual
        @SerialName("end_time") val endTime: OffsetDateTime,
        val id: String,
        @Contextual
        @SerialName("start_time") val startTime: OffsetDateTime,
        val worlds: WvwMatchWorldCount,
)
