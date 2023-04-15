package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for account achievement object
 */
@Serializable
data class AccountAchievement(
        val id: Int,
        val bits: List<Int>?,
        val current: Int?,
        val max: Int?,
        val done: Boolean,
        val repeated: Int?,
        val unlocked: Boolean?
)
