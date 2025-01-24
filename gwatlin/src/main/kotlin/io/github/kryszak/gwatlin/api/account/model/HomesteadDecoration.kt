package io.github.kryszak.gwatlin.api.account.model

import kotlinx.serialization.Serializable

/**
 * Data model for homestead decoration
 */
@Serializable
data class HomesteadDecoration(
    val id: Int,
    val count: Int
)
