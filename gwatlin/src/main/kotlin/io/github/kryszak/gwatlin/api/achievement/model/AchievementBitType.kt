package io.github.kryszak.gwatlin.api.achievement.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Possible values for achievement bit types.
 */
@Serializable
enum class AchievementBitType {
    @SerialName("Text")
    TEXT,
    @SerialName("Item")
    ITEM,
    @SerialName("Minipet")
    MINIPET,
    @SerialName("Skin")
    SKIN
}