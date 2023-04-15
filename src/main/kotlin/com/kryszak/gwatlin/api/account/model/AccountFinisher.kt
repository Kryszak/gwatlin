package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for finisher unlocked for account
 */
@Serializable
data class AccountFinisher(
        val id: Int,
        val permanent: Boolean,
        val quantity: Int?
)
