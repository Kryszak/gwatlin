package com.kryszak.gwatlin.clients.mapinfo

import com.kryszak.gwatlin.api.mapinfo.model.*
import com.kryszak.gwatlin.api.mapinfo.model.Map
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MapInfoClient : BaseHttpClient(
    "2022-03-23T19:00:00.000Z"
) {

    private val mapsEndpoint = "maps"
    private val continentsEndpoint = "continents"

    fun getMaps() = getRequest<List<Int>>(mapsEndpoint)

    fun getMaps(mapdIds: Collection<Int>, lang: String) =
        handleOneOrMultipleIds<Map>(mapdIds, lang, mapsEndpoint)

    fun getContinents() = getRequest<List<Int>>(continentsEndpoint)

    fun getContinents(continentIds: Collection<Int>, lang: String) =
        handleOneOrMultipleIds<Continent>(continentIds, lang, continentsEndpoint)

    fun getFloors(continentId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors")

    fun getFloor(continentId: Int, floorId: Int, lang: String) =
        getRequest<Floor>("$continentsEndpoint/$continentId/floors/$floorId?lang=$lang")

    fun getRegions(continentId: Int, floorId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors/$floorId/regions")

    fun getRegion(continentId: Int, floorId: Int, regionId: Int, lang: String) =
        getRequest<Region>("$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId?lang=$lang")

    fun getMaps(continentId: Int, floorId: Int, regionId: Int) =
        getRequest<List<Int>>("$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps")

    fun getMap(continentId: Int, floorId: Int, regionId: Int, mapId: Int, lang: String) =
        getRequest<ContinentMap>(
            "$continentsEndpoint/$continentId/floors/$floorId/regions/$regionId/maps/$mapId?lang=$lang"
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


    private inline fun <reified R : Any> handleOneOrMultipleIds(ids: Collection<Int>, lang: String, endpoint: String): List<R> {
        if (ids.isEmpty()) return emptyList()

        // The endpoint returns an array if two or more IDs are specified, but just the object if it's just one
        // so we need to handle that
        if (ids.size == 1) return listOf(getRequest("$endpoint/${ids.first()}?lang=$lang"))

        return getRequest("$endpoint?ids=${ids.joinToString(",")}&lang=$lang")
    }

}