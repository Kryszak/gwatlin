package io.github.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Possible values for upgrade actions
 */
@Serializable
enum class UpgradeAction {
    @SerialName("queued")
    QUEUED,
    @SerialName("cancelled")
    CANCELLED,
    @SerialName("completed")
    COMPLETED,
    @SerialName("sped_up")
    SPED_UP
}