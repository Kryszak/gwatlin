package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type HealingAdjust
 */
@Serializable
@SerialName("HealingAdjust")
data class HealingAdjust(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    @SerialName("hit_count")
    val hitCount: Int,
) : Fact
