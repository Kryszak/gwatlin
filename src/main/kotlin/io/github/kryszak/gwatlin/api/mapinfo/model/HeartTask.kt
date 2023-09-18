package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for Tasks, aka "Hearts"
 */
@Serializable
data class HeartTask(
    val id: Int,
    val objective: String,
    val level: Int,
    val coord: Coordinates,
    val bounds: List<Coordinates>,
    @SerialName("chat_link")
    val chatLink: String
)
