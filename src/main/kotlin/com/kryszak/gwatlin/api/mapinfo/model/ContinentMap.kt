package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName
import kotlin.collections.Map

/**
 * Data model for map info returned by the /continents endpoint
 */
data class ContinentMap(
    val id: Int,
    val name: String,
    @SerializedName("min_level")
    val minLevel: Int,
    @SerializedName("max_level")
    val maxLevel: Int,
    @SerializedName("default_floor")
    val defaultFloor: Int,
    @SerializedName("map_rect")
    val mapRect: Rectangle,
    @SerializedName("continent_rect")
    val continentRect: Rectangle,
    @SerializedName("label_coord")
    val labelCoord: Coordinates,
    @SerializedName("points_of_interest")
    val pointsOfInterest: Map<Int, PointOfInterest>,
    val tasks: Map<Int, HeartTask>,
    @SerializedName("skill_challenges")
    val skillChallenges: List<SkillChallenge>,
    val sectors: Map<Int, Sector>,
    val adventures: List<Adventure>,
    @SerializedName("mastery_points")
    val masteryPoints: List<MasteryPoint>
)
