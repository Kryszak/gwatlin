package io.github.kryszak.gwatlin.api.mapinfo

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.clients.mapinfo.MapInfoClient

/**
 * Client for characters endpoint. Includes the /maps and /continents endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMapInfoClient {

    private val mapInfoClient = MapInfoClient()

    /**
     * Returns a list of map IDs from the /maps endpoint
     */
    fun getMapIds() = mapInfoClient.getMapIds()

    /**
     * Returns a list of [io.github.kryszak.gwatlin.api.mapinfo.model.Map]
     * instances corresponding to the specified IDs from the /maps endpoint
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/maps)
     * @param mapIds a collection of map IDs
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getMaps(mapIds: Collection<Int>, language: ApiLanguage? = null) = mapInfoClient.getMaps(mapIds, language)

    /**
     * Returns a single [io.github.kryszak.gwatlin.api.mapinfo.model.Map]
     * instance corresponding to the specidfed ID from the /maps endpoint
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/maps)
     * @param mapId ID of the map to be fetched
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getMap(mapId: Int, language: ApiLanguage? = null) = getMaps(listOf(mapId), language).firstOrNull()

    /**
     * Returns a list of continent IDs
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     */
    fun getContinentIds() = mapInfoClient.getContinentIds()

    /**
     * Returns the continent corresponding to the specified ID, or null if no such continent exists
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent to be fetched
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    @JvmOverloads
    fun getContinent(continentId: Int, language: ApiLanguage? = null) =
        mapInfoClient.getContinents(listOf(continentId), language).firstOrNull()

    /**
     * Returns the continent list corresponding to the specified ID list.
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentIds ID list of the continents to be fetched
     * @param language one of the languages defined in [ApiLanguage]
     * when creating this client, if any
     */
    fun getContinents(continentIds: Collection<Int>, language: ApiLanguage? = null) =
        mapInfoClient.getContinents(continentIds, language)

    /**
     * Returns a list of floor IDs on the specified continent
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent
     */
    fun getFloorIds(continentId: Int) =
        mapInfoClient.getFloorIds(continentId)

    /**
     * Returns the floor corresponding to the specified continentId and floorID
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
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
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     */
    fun getRegionIds(continentId: Int, floorId: Int) =
        mapInfoClient.getRegionIds(continentId, floorId)

    /**
     * Returns the region corresponding to the specified continentId, floorId and regionId
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
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
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
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
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
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
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getSectors(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getSectors(continentId, floorId, regionId, mapId)

    /**
     * Returns a list of points of interest (aka POI) IDs on the specified map
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getPointsOfInterestIds(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getPointsOfInterestIds(continentId, floorId, regionId, mapId)

    /**
     * Returns a list of tasks (aka Hearts) IDs on the specified map
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/continents)
     * @param continentId ID of the continent
     * @param floorId ID of the floor on the continent
     * @param regionId ID of the region on the floor
     * @param mapId ID of the map in the region
     */
    fun getTaskIds(continentId: Int, floorId: Int, regionId: Int, mapId: Int) =
        mapInfoClient.getTaskIds(continentId, floorId, regionId, mapId)
}