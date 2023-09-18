package io.github.kryszak.gwatlin.api.gamemechanics.model.mastery

import kotlinx.serialization.Serializable

/**
 * Data model for Mastery object
 */
@Serializable
data class Mastery(
        val id: Int,
        val name: String,
        val requirement: String,
        val order: Int,
        val background: String,
        val region: String,
        val levels: List<MasteryLevel>
)