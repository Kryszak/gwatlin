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
    val labelCoord: Coordinates,
    @SerialName("points_of_interest")
    val pointsOfInterest: Map<Int, PointOfInterest>,
    val tasks: Map<Int, HeartTask>,
    @SerialName("skill_challenges")
    val skillChallenges: List<SkillChallenge>,
    val sectors: Map<Int, Sector>,
    val adventures: List<Adventure>,
    @SerialName("mastery_points")
    val masteryPoints: List<MasteryPoint>
)
