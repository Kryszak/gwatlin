package com.kryszak.gwatlin.api.mapinfo

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.clients.mapinfo.MapInfoClient

/**
 * Client for characters endpoint. Includes the /maps and /continents endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMapInfoClient {

    private val mapInfoClient = MapInfoClient()

    /**
     * Returns a list of map IDs from the /maps endpoint
     */
    fun getMaps() = mapInfoClient.getMaps()

    /**
     * Returns a list of [com.kryszak.gwatlin.api.mapinfo.model.Map]
     * instances corresponding to the specified IDs from the /maps endpoint
     * @param mapIds a collection of map IDs
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getMaps(mapIds: Collection<Int>, language: ApiLanguage? = null) = mapInfoClient.getMaps(mapIds, language)

    /**
     * Returns a single [com.kryszak.gwatlin.api.mapinfo.model.Map]
     * instance corresponding to the specidfed ID from the /maps endpoint
     * @param mapId ID of the map to be fetched
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getMap(mapId: Int, language: ApiLanguage? = null) = getMaps(listOf(mapId), language).firstOrNull()

    /**
     * Returns a list of continent IDs
     */
    fun getContinents() = mapInfoClient.getContinents()

    /**
     * Returns the continent corresponding to the specified ID, or null if no such continent exists
     * @param continentId ID of the continent to be fetched
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getContinent(continentId: Int, language: ApiLanguage? = null) =
        mapInfoClient.getContinents(listOf(continentId), language).firstOrNull()

    /**
     * Returns a list of floor IDs on the specified continent
     * @param continentId ID of the continent
     */
    fun getFloors(continentId: Int) =
        mapInfoClient.getFloors(continentId)

    /**
     * Returns the floor corresponding to the specified continentId and floorID
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getFloor(continentId: Int, floorId: Int, language: ApiLanguage? = null) =
        mapInfoClient.getFloor(continentId, floorId, language)

    /**
     * Returns a list of region IDs on the specified continent and floor
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     */
    fun getRegions(continentId: Int, floorId: Int) =
        mapInfoClient.getRegions(continentId, floorId)

    /**
     * Returns the region corresponding to the specified continentId, floorId and regionId
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getRegion(continentId: Int, floorId: Int, regionId: Int, language: ApiLanguage? = null) =
        mapInfoClient.getRegion(continentId, floorId, regionId, language)

    /**
     * Returns a list of region IDs on the specified continent, floor and region
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     */
    fun getMaps(continentId: Int, floorId: Int, regionId: Int) =
        mapInfoClient.getMaps(continentId, floorId, regionId)

    /**
     * Returns the map corresponding to the specified continentId, floorId, regionId and mapId. This function differs
     * from the other [getMap] function in that it uses the /continents endpoint instead of the /maps endpoint, resulting
     * in a different result object.
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getMap(continentId: Int, floorId: Int, regionId: Int, mapId: Int, language: ApiLanguage? = null) =
        mapInfoClient.getMap(continentId, floorId, regionId, mapId, language)

    /**
     * Returns a list of sector IDs on the specified map
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getSectors(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getSectors(continentId, floorId, regionId, mapId)

    /**
     * Returns a list of points of interest (aka POI) IDs on the specified map
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getPointsOfInterest(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getPointsOfInterest(continentId, floorId, regionId, mapId)

    /**
     * Returns a list of tasks (aka Hearts) IDs on the specified map
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getTasks(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getTasks(continentId, floorId, regionId, mapId)
}