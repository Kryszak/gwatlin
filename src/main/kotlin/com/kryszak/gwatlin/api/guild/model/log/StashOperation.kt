package com.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName

/**
 * Possible values for stash operations
 */
enum class StashOperation {
    @SerialName("deposit")
    DEPOSIT,
    @SerialName("withdraw")
    WITHDRAW,
    @SerialName("move")
    MOVE
}