package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountProgression(
    val id: String,
    val value: Long,
)
