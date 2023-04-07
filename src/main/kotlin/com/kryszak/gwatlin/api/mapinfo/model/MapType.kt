package com.kryszak.gwatlin.api.mapinfo.model

import com.google.gson.annotations.SerializedName

/**
 * Enumeration for the possible types of a map
 */
enum class MapType {
    // WvW maps
    @SerializedName("Center")
    CENTER,
    @SerializedName("RedHome")
    RED_HOME,
    @SerializedName("BlueHome")
    BLUE_HOME,
    @SerializedName("GreenHome")
    GREEN_HOME,
    @SerializedName("EdgeOfTheMists")
    EDGE_OF_THE_MISTS,

    // PvP
    @SerializedName("Pvp")
    PVP,

    // PvE and other
    @SerializedName("Instance")
    INSTANCE,
    @SerializedName("JumpPuzzle")
    JUMP_PUZZLE,
    @SerializedName("Public")
    PUBLIC,
    @SerializedName("Tutorial")
    TUTORIAL,
    @SerializedName("Unknown")
    UNKNOWN
}