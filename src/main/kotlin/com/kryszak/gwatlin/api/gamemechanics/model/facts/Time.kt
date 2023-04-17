package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Time")
data class Time(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val duration: Float,
) : Fact
