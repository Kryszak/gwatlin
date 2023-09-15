package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type Range
 */
@Serializable
@SerialName("Range")
data class Range(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val value: Int,
) : Fact
