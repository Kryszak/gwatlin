package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName

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