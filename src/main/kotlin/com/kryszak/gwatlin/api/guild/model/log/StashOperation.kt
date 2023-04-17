package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName

enum class StashOperation {
    @SerialName("deposit")
    DEPOSIT,
    @SerialName("withdraw")
    WITHDRAW,
    @SerialName("move")
    MOVE
}