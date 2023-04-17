package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for skill/trait facts with type PrefixedBuff
 */
@Serializable
@SerialName("PrefixedBuff")
data class PrefixedBuff(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val duration: Int? = null,
    val status: String? = null,
    val description: String? = null,
    @SerialName("apply_count")
    val applyCount: Int? = null,
    val prefix: BuffPrefix
) : Fact
