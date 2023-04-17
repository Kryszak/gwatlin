package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("BuffConversion")
data class BuffConversion(
    override val type: String,
    override val text: String? = null,
    override val icon: String? = null,
    val percent: Float,
    val source: String,
    val target: String
) : Fact
