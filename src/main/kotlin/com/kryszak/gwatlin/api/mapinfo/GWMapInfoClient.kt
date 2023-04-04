package com.kryszak.gwatlin.api.mapinfo

import com.kryszak.gwatlin.clients.mapinfo.MapInfoClient

/**
 * Client for characters endpoint. Includes the /map and /continents endpoints
 * @param defaultLang the language to be used if no other language is specified when calling a specific method
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMapInfoClient(
    private val defaultLang: String = "en"
) {

    private val mapInfoClient = MapInfoClient()

    fun getMaps() = mapInfoClient.getMaps()

    @JvmOverloads
    fun getMaps(mapIds: Collection<Int>, lang: String = defaultLang) = mapInfoClient.getMaps(mapIds, lang)

    @JvmOverloads
    fun getMap(mapId: Int, lang: String = defaultLang) = getMaps(listOf(mapId), lang).firstOrNull()

    fun getContinents() = mapInfoClient.getContinents()

    @JvmOverloads
    fun getContinent(continentId: Int, lang: String = defaultLang) =
        mapInfoClient.getContinents(listOf(continentId), lang).firstOrNull()

    fun getFloors(continentId: Int) =
        mapInfoClient.getFloors(continentId)

    @JvmOverloads
    fun getFloor(continentId: Int, floorId: Int, lang: String = defaultLang) =
        mapInfoClient.getFloor(continentId, floorId, lang)

    fun getRegions(continentId: Int, floorId: Int) =
        mapInfoClient.getRegions(continentId, floorId)

    @JvmOverloads
    fun getRegion(continentId: Int, floorId: Int, regionId: Int, lang: String = defaultLang) =
        mapInfoClient.getRegion(continentId, floorId, regionId, lang)

    fun getMaps(continentId: Int, floorId: Int, regionId: Int) =
        mapInfoClient.getMaps(continentId, floorId, regionId)

    @JvmOverloads
    fun getMap(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String = defaultLang) =
        mapInfoClient.getMap(continentId, floorId, regionId, mapId, lang)

    fun getSectors(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getSectors(continentId, floorId, regionId, mapId)

    fun getPointsOfInterest(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getPointsOfInterest(continentId, floorId, regionId, mapId)

    fun getTasks(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getTasks(continentId, floorId, regionId, mapId)
}