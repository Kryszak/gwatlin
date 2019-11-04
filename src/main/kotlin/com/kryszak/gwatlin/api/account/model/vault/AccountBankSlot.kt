package com.kryszak.gwatlin.api.account.model.vault

import com.google.gson.annotations.SerializedName

/**
 * Data model for player's vault slot object
 */
data class AccountBankSlot(
        val id: Int,
        val count: Int,
        val charges: Int?,
        val skin: Int?,
        val upgrades: List<Int>?,
        val infusions: List<Int>?,
        val binding: String?,
        @SerializedName("bound_to") val boundTo: String?
)
