package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type AttributeAdjust
 */
@Serializable
@SerialName("AttributeAdjust")
data class AttributeAdjust(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val value: Int,
    val target: String
) : Fact
