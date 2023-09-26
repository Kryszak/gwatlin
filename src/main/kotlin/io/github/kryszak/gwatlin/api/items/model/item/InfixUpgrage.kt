package io.github.kryszak.gwatlin.api.items.model.item

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfixUpgradeAttribute(
    val attribute: String,
    val modifier: Double
)

@Serializable
data class InfixUpgradeBuff(
    val flags: List<String>,
    @SerialName("item_id") val itemId: Int
)

@Serializable
data class InfixUpgrade(
    val id: Int? = null,
    val attributes: List<InfixUpgradeAttribute>,
    val buff: InfixUpgradeBuff? = null
)