package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.collections.Map

/**
 * Data model for map info returned by the /continents endpoint
 */
@Serializable
data class ContinentMap(
    val id: Int,
    val name: String,
    @SerialName("min_level")
    val minLevel: Int,
    @SerialName("max_level")
    val maxLevel: Int,
    @SerialName("default_floor")
    val defaultFloor: Int,
    @SerialName("map_rect")
    val mapRect: Rectangle,
    @SerialName("continent_rect")
    val continentRect: Rectangle,
    @SerialName("label_coord")
    val labelCoord: Coordinates? = null,
    @SerialName("points_of_interest")
    val pointsOfInterest: Map<Int, PointOfInterest> = mapOf(),
    val tasks: Map<Int, HeartTask> = mapOf(),
    @SerialName("skill_challenges")
    val skillChallenges: List<SkillChallenge> = listOf(),
    val sectors: Map<Int, Sector> = mapOf(),
    val adventures: List<Adventure> = listOf(),
    @SerialName("mastery_points")
    val masteryPoints: List<MasteryPoint> = listOf(),
)
