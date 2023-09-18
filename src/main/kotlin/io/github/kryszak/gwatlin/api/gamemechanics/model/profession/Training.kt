package io.github.kryszak.gwatlin.api.gamemechanics.model.profession

import kotlinx.serialization.Serializable

/**
 * Data model for training property of profession object
 */
@Serializable
data class Training(
        val id: Int,
        val category: TrainingCategory,
        val name: String,
        val track: List<TrainingTrack>
)
