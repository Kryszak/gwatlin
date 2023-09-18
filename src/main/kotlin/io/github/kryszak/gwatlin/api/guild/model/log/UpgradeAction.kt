package io.github.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName

/**
 * Possible values for upgrade actions
 */
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