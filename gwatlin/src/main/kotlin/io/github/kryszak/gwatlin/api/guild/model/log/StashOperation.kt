package io.github.kryszak.gwatlin.api.guild.model.log

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Possible values for stash operations
 */
@Serializable
enum class StashOperation {
    @SerialName("deposit")
    DEPOSIT,
    @SerialName("withdraw")
    WITHDRAW,
    @SerialName("move")
    MOVE
}