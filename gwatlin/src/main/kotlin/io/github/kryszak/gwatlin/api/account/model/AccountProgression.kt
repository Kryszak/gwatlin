package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for account progression
 */
@Serializable
data class AccountProgression(
    val id: String,
    val value: Long,
)
