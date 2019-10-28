package com.kryszak.gwatlin.api.gamemechanics.model.profession

/**
 * Data model for training property of profession object
 */
data class Training(
        val id: Int,
        val category: TrainingCategory,
        val name: String,
        val track: List<TrainingTrack>
)
