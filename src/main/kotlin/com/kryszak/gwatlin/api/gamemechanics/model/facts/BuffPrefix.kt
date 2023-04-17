package com.kryszak.gwatlin.api.gamemechanics.model.facts

import kotlinx.serialization.Serializable

@Serializable
data class BuffPrefix(
    val test: String,
    val icon: String,
    val status: String,
    val description: String
)
