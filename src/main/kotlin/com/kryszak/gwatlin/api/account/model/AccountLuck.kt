package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for account luck
 */
@Serializable
data class AccountLuck(
        val id: String,
        val value: Int
)
