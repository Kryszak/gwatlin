package io.github.kryszak.gwatlin.api.gamemechanics.model.raid

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Types of raid wing events
 */
@Serializable
enum class RaidWingEventType {
    @SerialName("Checkpoint")
    CHECKPOINT,

    @SerialName("Boss")
    BOSS,
}