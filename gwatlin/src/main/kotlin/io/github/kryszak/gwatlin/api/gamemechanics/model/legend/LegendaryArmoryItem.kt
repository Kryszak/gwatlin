package io.github.kryszak.gwatlin.api.gamemechanics.model.legend

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for legendary item armory response object
 */
@Serializable
data class LegendaryArmoryItem(
    val id: Long,
    @SerialName("max_count")
    val maxCount: Int,
)
