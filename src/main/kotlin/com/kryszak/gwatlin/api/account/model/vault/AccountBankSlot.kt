package com.kryszak.gwatlin.api.account.model.vault

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data model for player's vault slot object
 */
@Serializable
data class AccountBankSlot(
        val id: Int,
        val count: Int,
        val charges: Int?,
        val skin: Int?,
        val upgrades: List<Int>?,
        val infusions: List<Int>?,
        val binding: String?,
        @SerialName("bound_to") val boundTo: String?
)
