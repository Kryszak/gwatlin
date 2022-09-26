package com.kryszak.gwatlin.api.mapinfo

import com.kryszak.gwatlin.api.mapinfo.model.Continent
import com.kryszak.gwatlin.api.mapinfo.model.ContinentMap
import com.kryszak.gwatlin.api.mapinfo.model.Floor
import com.kryszak.gwatlin.api.mapinfo.model.Region
import com.kryszak.gwatlin.clients.mapinfo.MapInfoClient

/**
 * Client for characters endpoint. Includes the /map and /contintens endpoints
 * @param defaultLang the language to be used if no other language is specified when calling a specific method
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMapInfoClient(
    private val defaultLang: String = "en"
) {

    private val mapInfoClient = MapInfoClient()

    fun getMaps() = mapInfoClient.getMaps()

    fun getMaps(mapIds: Collection<Int>, lang: String = defaultLang) = mapInfoClient.getMaps(mapIds, lang)

    fun getMap(mapId: Int, lang: String = defaultLang) = getMaps(listOf(mapId), lang).firstOrNull()

    fun getContinents() = mapInfoClient.getContinents()

    fun getContinents(continentIds: Collection<Int>, lang: String = defaultLang) =
        mapInfoClient.getContinents(continentIds, lang)

    fun getContinent(continentId: Int, lang: String = defaultLang) =
        mapInfoClient.getContinents(listOf(continentId), lang).firstOrNull()

    fun getFloors(continentId: Int, lang: String = defaultLang) =
        mapInfoClient.getFloors(continentId, lang)

    fun getFloor(continentId: Int, floorId: Int, lang: String = defaultLang) =
        mapInfoClient.getFloor(continentId, floorId, lang)

    fun getRegions(continentId: Int, floorId: Int, lang: String = defaultLang) =
        mapInfoClient.getRegions(continentId, floorId, lang)

    fun getRegion(continentId: Int, floorId: Int, regionId: Int, lang: String = defaultLang) =
        mapInfoClient.getRegion(continentId, floorId, regionId, lang)

    fun getMaps(continentId: Int, floorId: Int, regionId: Int, lang: String = defaultLang) =
        mapInfoClient.getMaps(continentId, floorId, regionId, lang)

    fun getMap(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String = defaultLang) =
        mapInfoClient.getMaps(continentId, floorId, regionId, lang)

    fun getSectors(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String = defaultLang) =
        mapInfoClient.getSectors(continentId, floorId, regionId, mapId, lang)

    fun getPointsOfInterest(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String = defaultLang) =
        mapInfoClient.getPointsOfInterest(continentId, floorId, regionId, mapId, lang)

    fun getTasks(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String = defaultLang) =
        mapInfoClient.getTasks(continentId, floorId, regionId, mapId, lang)
}