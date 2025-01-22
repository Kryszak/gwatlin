package io.github.kryszak.gwatlin.api.account.model.vault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for player's vault slot object
 */
@Serializable
data class AccountBankSlot(
        val id: Int,
        val count: Int,
        val charges: Int? = null,
        val skin: Int? = null,
        val upgrades: List<Int> = listOf(),
        val infusions: List<Int> = listOf(),
        val binding: String? = null,
        @SerialName("bound_to") val boundTo: String? = null
)
