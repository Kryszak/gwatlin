package com.kryszak.gwatlin.api.account.model.vault

/**
 * Data model for material in player's vault
 */
data class AccountMaterial(
        val id: Int,
        val category: Int,
        val binding: String?,
        val count: Int
)
