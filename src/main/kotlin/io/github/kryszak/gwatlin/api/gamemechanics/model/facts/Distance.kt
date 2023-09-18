package io.github.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type Distance
 */
@Serializable
@SerialName("Distance")
data class Distance(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val distance: Int
) : Fact
