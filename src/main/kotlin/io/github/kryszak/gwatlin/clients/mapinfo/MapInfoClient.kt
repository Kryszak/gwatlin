package io.github.kryszak.gwatlin.clients.mapinfo

import io.github.kryszak.gwatlin.api.mapinfo.model.*
import io.github.kryszak.gwatlin.api.mapinfo.model.Map
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MapInfoClient : BaseHttpClient(
    "2022-03-23T19:00:00.000Z"
) {

    private val mapsEndpoint = "/maps"
    private val continentsEndpoint = "/continents"

    fun getMaps() = getRequest<List<Int>>(mapsEndpoint)

    fun getMaps(mapIds: Collection<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?) =
        handleOneOrMultipleIds<Map>(mapIds, language, mapsEndpoint)

    fun getContinents() = getRequest<List<Int>>(continentsEndpoint)

    fun getContinents(continentIds: Collection<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?) =
        handleOneOrMultipleIds<Continent>(continentIds, language, continentsEndpoint)

    fun getFloors(continentId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors")

    fun getFloor(continentId: Int, floorId: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage?) =
        getRequest<Floor>("$continentsEndpoint/$continentId/floors/$floorId", language)

    fun getRegions(continentId: Int, floorId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors/$floorId/regions")

    fun getRegion(continentId: Int, floorId: Int, regionId: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage?) =
        getRequest<Region>("$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId", language)

    fun getMaps(continentId: Int, floorId: Int, regionId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps")

    fun getMap(
        continentId: Int,
        floorId: Int,
        regionId: Int,
        mapId: Int,
        language: io.github.kryszak.gwatlin.api.ApiLanguage?
    ) =
        getRequest<ContinentMap>(
            "$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps/$mapId", language
        )

    fun getSectors(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        getRequest<List<Int>>(
            "$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/sectors"
        )

    fun getPointsOfInterest(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        getRequest<List<Int>>(
            "$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/pois"
        )

    fun getTasks(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        getRequest<List<Int>>(
            "$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/tasks"
        )

    private inline fun <reified R : Any> handleOneOrMultipleIds(
        ids: Collection<Int>,
        language: io.github.kryszak.gwatlin.api.ApiLanguage?,
        endpoint: String
    ) = when {
        ids.isEmpty() -> emptyList<R>()
        ids.size == 1 -> listOf(getRequest("$endpoint/${ids.first()}", language))
        else -> getRequest("$endpoint?ids=${ids.joinToString(",")}", language)
    }

}