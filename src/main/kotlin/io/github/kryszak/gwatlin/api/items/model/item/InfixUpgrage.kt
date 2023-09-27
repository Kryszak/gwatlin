package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for infix upgrade attribute
 */
@Serializable
data class InfixUpgradeAttribute(
    val attribute: String,
    val modifier: Double
)

/**
 * Data model for infix upgrade buff
 */
@Serializable
data class InfixUpgradeBuff(
    val description: String = "",
    @SerialName("skill_id") val itemId: Int
)

/**
 * Data model for infix upgrade
 */
@Serializable
data class InfixUpgrade(
    val id: Int? = null,
    val attributes: List<InfixUpgradeAttribute> = listOf(),
    val buff: InfixUpgradeBuff? = null
)