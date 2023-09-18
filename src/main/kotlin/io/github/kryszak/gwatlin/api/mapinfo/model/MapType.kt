package io.github.kryszak.gwatlin.api.mapinfo.model

import kotlinx.serialization.SerialName

/**
 * Enumeration for the possible types of a map
 */
enum class MapType {
    // WvW maps
    @SerialName("Center")
    CENTER,
    @SerialName("RedHome")
    RED_HOME,
    @SerialName("BlueHome")
    BLUE_HOME,
    @SerialName("GreenHome")
    GREEN_HOME,
    @SerialName("EdgeOfTheMists")
    EDGE_OF_THE_MISTS,

    // PvP
    @SerialName("Pvp")
    PVP,

    // PvE and other
    @SerialName("Instance")
    INSTANCE,
    @SerialName("JumpPuzzle")
    JUMP_PUZZLE,
    @SerialName("Public")
    PUBLIC,
    @SerialName("Tutorial")
    TUTORIAL,
    @SerialName("Unknown")
    UNKNOWN
}