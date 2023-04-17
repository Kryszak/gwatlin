package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Heal")
data class Heal(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    @SerialName("hit_count")
    val hitCount: Int,
) : Fact
