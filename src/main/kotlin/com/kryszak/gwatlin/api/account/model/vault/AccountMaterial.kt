package com.kryszak.gwatlin.api.account.model.vault

import kotlinx.serialization.Serializable

/**
 * Data model for material in player's vault
 */
@Serializable
data class AccountMaterial(
        val id: Int,
        val category: Int,
        val binding: String?,
        val count: Int
)
