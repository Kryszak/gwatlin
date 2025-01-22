package io.github.kryszak.e2e.mapinfo

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.mapinfo.GWMapInfoClient
import io.kotest.assertions.throwables.shouldNotThrowAny

class MapInfoE2ETests : BaseE2ESpec() {
    private val client = GWMapInfoClient()

    init {
        context("Map info") {
            expect("Fetch random maps") {
                val mapInfoIds = client.getMapIds().randomElements(100)
                shouldNotThrowAny { client.getMaps(mapInfoIds) }
            }
            expect("Fetch continents") {
                val continentId = client.getContinentIds().random()
                shouldNotThrowAny { client.getContinent(continentId) }
            }
            expect("Fetch region") {
                val continentId = client.getContinentIds().random()
                val floorId = client.getFloorIds(continentId).random()
                val regionId = client.getRegionIds(continentId, floorId).random()
                shouldNotThrowAny { client.getRegion(continentId, floorId, regionId) }
            }
            context("Map details") {
                val continentId = client.getContinentIds().random()
                var floorId = client.getFloorIds(continentId).random()
                var floor = client.getFloor(continentId, floorId)
                while (floor.regions.isEmpty()) {
                    floorId = client.getFloorIds(continentId).random()
                    floor = client.getFloor(continentId, floorId)
                }
                val regionId = client.getRegionIds(continentId, floorId).random()
                val mapId = client.getMaps(continentId, floorId, regionId).random()
                expect("Fetch POI") {
                    shouldNotThrowAny { client.getPointsOfInterestIds(continentId, floorId, regionId, mapId) }
                }
                expect("Fetch task") {
                    shouldNotThrowAny { client.getTaskIds(continentId, floorId, regionId, mapId) }
                }
            }
        }
    }
}