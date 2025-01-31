package io.github.kryszak.gwatlin.api.wizardsvault.model

import kotlinx.serialization.Serializable

/**
 * Data model for wizard's vault season objective
 */
@Serializable
data class Objective(
    val id: Int,
    val title: String,
    val track: String,
    val acclaim: Int
)
