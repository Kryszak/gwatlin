package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.Serializable

/**
 * Data model for skill challenges, aka "Hero Point"
 */
@Serializable
data class SkillChallenge(
    val id: String? = null,
    val coord: Coordinates,
)
