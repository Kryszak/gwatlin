package com.kryszak.gwatlin.api.account.model

/**
 * Data model for finisher unlocked for account
 */
data class AccountFinisher(
        val id: Int,
        val permanent: Boolean,
        val quantity: Int?
)
