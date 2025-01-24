package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

@Serializable
data class HomesteadDecoration(
    val id: Int,
    val count: Int
)
