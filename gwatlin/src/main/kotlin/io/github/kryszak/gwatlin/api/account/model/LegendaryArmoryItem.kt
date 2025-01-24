package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for account's legendary armory
 */
@Serializable
data class LegendaryArmoryItem(
    val id: Int,
    val count: Int,
)
