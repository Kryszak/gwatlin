package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

@Serializable
data class LegendaryArmoryItem(
    val id: Int,
    val count: Int,
)
