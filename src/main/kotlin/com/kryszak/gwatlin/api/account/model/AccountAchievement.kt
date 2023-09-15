package com.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for account achievement object
 */
@Serializable
data class AccountAchievement(
        val id: Int,
        val bits: List<Int> = listOf(),
        val current: Int? = null,
        val max: Int? = null,
        val done: Boolean,
        val repeated: Int? = null,
        val unlocked: Boolean? = null
)
