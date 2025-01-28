package io.github.kryszak.gwatlin.api.mapinfo

import io.github.kryszak.gwatlin.api.mapinfo.model.Dimensions
import io.github.kryszak.gwatlin.api.mapinfo.model.MapType
import io.github.kryszak.gwatlin.api.mapinfo.model.PointOfInterestType
import io.github.kryszak.gwatlin.api.mapinfo.model.Rectangle
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class MapInfoClientTest : BaseWiremockTest() {

    private val mapInfoClient = GWMapInfoClient()

    init {
        should("Get continent map") {
            // given
            val continentId = 1
            val floorId = 1
            val regionId = 1
            val mapId = 26

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId",
                "/responses/mapinfo/continentMap.json"
            )

            // when
            val result = mapInfoClient.getMap(continentId, floorId, regionId, mapId)

            // then
            assertSoftly(result) {
                name shouldBe "Dredgehaunt Cliffs"
                id shouldBe 26
                minLevel shouldBe 40
                maxLevel shouldBe 50
                defaultFloor shouldBe 1
                labelCoord.x shouldBe 53376
                labelCoord.y shouldBe 32960
                mapRect.first.x shouldBe -27648
                mapRect.second.y shouldBe 39936
                continentRect.first.x shouldBe 52224
                continentRect.second.y shouldBe 34560
                pointsOfInterest shouldHaveSize 51
                assertSoftly(pointsOfInterest[603]!!) {
                    name shouldBe "Steelbrachen Waypoint"
                    id shouldBe 603
                    type shouldBe PointOfInterestType.WAYPOINT
                    floor shouldBe 1
                    chatLink shouldBe "[&BFsCAAA=]"
                    coord.y shouldBe 33489
                }
                assertSoftly(pointsOfInterest[1732]!!) {
                    type shouldBe PointOfInterestType.UNLOCK
                    icon shouldBe "https://render.guildwars2.com/file/943538394A94A491C8632FBEF6203C2013443555/102478.png"
                }
                tasks shouldHaveSize 11
                assertSoftly(tasks[5]!!) {
                    level shouldBe 47
                    coord.x shouldBe 54217.1f
                    coord.y shouldBe 33882.8f
                    id shouldBe 5
                    chatLink shouldBe "[&BAUAAAA=]"
                    bounds shouldHaveSize 18
                    bounds[2].x shouldBe 54255.6f
                    objective shouldBe "Help Arcanist Vance study dwarven relics at Granite Citadel."
                }
                skillChallenges shouldHaveSize 6
                assertSoftly(skillChallenges[4]) {
                    id shouldBe "0-58"
                    coord.x shouldBe 53436.7f
                }
                sectors shouldHaveSize 28
                assertSoftly(sectors[517]!!) {
                    level shouldBe 49
                    name shouldBe "Kapellenburg"
                    bounds shouldHaveSize 8
                    id shouldBe 517
                    chatLink shouldBe "[&BAUCAAA=]"
                    coord.y shouldBe 33705.1f
                }
                adventures shouldHaveSize 2
                assertSoftly(adventures.first()) {
                    coord.x shouldBe 37316.5f
                    id shouldBe "8D00FA87-28CD-4402-AAEF-501A610E0447"
                    name shouldBe "Beetle Feast"
                    description shouldBe "Eat yellow mushrooms to score points and blue mushrooms to gain abilities that help you in your hunt. Beware of the poisonous mushrooms with green spores, and use your burrow ability to bypass closed gates!"
                }
                masteryPoints shouldHaveSize 1
                assertSoftly(masteryPoints.first()) {
                    id shouldBe 319
                    coord.x shouldBe 53190.8f
                    region.lowercase() shouldBe "tyria"
                }
            }
        }

        should("Get continent ids") {
            // given
            stubResponse(
                "/v2/continents",
                "/responses/mapinfo/continents.json"
            )

            // when
            val result = mapInfoClient.getContinentIds()

            // then
            result shouldContainExactly listOf(1, 2)
        }

        should("Get continent") {
            // given
            val continentId = 1

            stubResponse(
                "/v2/continents/$continentId",
                "/responses/mapinfo/continentOne.json"
            )

            // when
            val result = mapInfoClient.getContinent(continentId)

            // then
            assertSoftly(result!!) {
                id shouldBe 1
                name shouldBe "Tyria"
                continentDims.x shouldBe 81920
                continentDims.y shouldBe 114688
                minZoom shouldBe 0
                maxZoom shouldBe 8
                floors shouldHaveSize 119
            }
        }

        should("Get multiple continents") {
            // given
            val continentIds = listOf(1, 2)

            stubResponse(
                "/v2/continents?ids=1,2",
                "/responses/mapinfo/continentsMultiple.json"
            )

            // when
            val result = mapInfoClient.getContinents(continentIds)

            // then
            assertSoftly(result) {
                size shouldBe 2
                assertSoftly(it[0]) {
                    id shouldBe 1
                    name shouldBe "Tyria"
                    continentDims.x shouldBe 81920
                    continentDims.y shouldBe 114688
                    minZoom shouldBe 0
                    maxZoom shouldBe 8
                    floors shouldHaveSize 119
                }
                assertSoftly(it[1]) {
                    id shouldBe 2
                    name shouldBe "Mists"
                    continentDims.x shouldBe 16384
                    continentDims.y shouldBe 16384
                    minZoom shouldBe 0
                    maxZoom shouldBe 6
                    floors shouldHaveSize 48
                }
            }
        }

        should("Get floors") {
            // given
            val continentId = 1

            stubResponse(
                "/v2/continents/$continentId/floors",
                "/responses/mapinfo/continentOneFloors.json"
            )

            // when
            val result = mapInfoClient.getFloorIds(continentId)

            // then
            result shouldHaveSize 119
        }

        should("Get floor") {
            // given
            val continentId = 1
            val floorId = 6

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId",
                "/responses/mapinfo/continentOneFloor6.json"
            )

            // when
            val result = mapInfoClient.getFloor(continentId, floorId)

            // then
            assertSoftly(result) {
                textureDims.x shouldBe 81920
                textureDims.y shouldBe 114688

                assertSoftly(clampedView!!) {
                    first.x shouldBe 54272
                    second.y shouldBe 25856
                }

                regions shouldHaveSize 1
            }
        }

        should("Get regions") {
            // given
            val continentId = 1
            val floorId = 6

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions",
                "/responses/mapinfo/continentOneFloor6Regions.json"
            )

            // when
            val result = mapInfoClient.getRegionIds(continentId, floorId)

            // then
            result shouldContainExactly listOf(1)
        }

        should("Get region") {
            // given
            val continentId = 1
            val floorId = 6
            val regionId = 6

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId",
                "/responses/mapinfo/continentOneFloor6Region1.json"
            )

            // when
            val result = mapInfoClient.getRegion(continentId, floorId, regionId)

            // then
            assertSoftly(result) {
                name shouldBe "Shiverpeak Mountains"
                id shouldBe 1
                labelCoord.x shouldBe 52608
                continentRect.first.x shouldBe 50432
                continentRect.second.y shouldBe 37760
                maps shouldHaveSize 2
            }
        }

        should("Get continent maps") {
            // given
            val continentId = 1
            val floorId = 6
            val regionId = 6

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId/maps",
                "/responses/mapinfo/continentOneFloor6Region1Maps.json"
            )

            // when
            val result = mapInfoClient.getMaps(continentId, floorId, regionId)

            // then
            result shouldContainExactly listOf(70, 71)
        }

        should("Get all map ids") {
            // given
            stubResponse(
                "/v2/maps",
                "/responses/mapinfo/maps.json"
            )

            // when
            val result = mapInfoClient.getMapIds()

            // then
            result shouldHaveSize 937
        }

        should("Get multiple maps") {
            // given
            val ids = listOf(26, 27)

            stubResponse(
                "/v2/maps?ids=${ids.joinToString(",")}",
                "/responses/mapinfo/mapsMultiple.json"
            )

            // when
            val result = mapInfoClient.getMaps(ids)

            // then
            result shouldHaveSize 2
        }

        should("Ghould get map") {
            // given
            val mapId = 26

            stubResponse(
                "/v2/maps/$mapId",
                "/responses/mapinfo/map26.json"
            )

            // when
            val result = mapInfoClient.getMap(mapId)

            // then
            assertSoftly(result!!) {
                id shouldBe 26
                name shouldBe "Dredgehaunt Cliffs"
                minLevel shouldBe 40
                maxLevel shouldBe 50
                defaultFloor shouldBe 1
                type shouldBe MapType.PUBLIC
                floors shouldContainExactly listOf(1, 3, 2, 0)
                regionId shouldBe 1
                regionName shouldBe "Shiverpeak Mountains"
                continentId shouldBe 1
                continentName shouldBe "Tyria"
                mapRect.first.x shouldBe -27648
                continentRect.first.x shouldBe 52224
            }
        }

        should("Ghould get tasks") {
            // given
            val continentId = 1
            val floorId = 1
            val regionId = 1
            val mapId = 26

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/tasks",
                "/responses/mapinfo/tasks.json"
            )

            // when
            val result = mapInfoClient.getTaskIds(continentId, floorId, regionId, mapId)

            // then
            result shouldHaveSize 11
        }

        should("Ghould get POIs") {
            // given
            val continentId = 1
            val floorId = 1
            val regionId = 1
            val mapId = 26

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/pois",
                "/responses/mapinfo/pois.json"
            )

            // when
            val result = mapInfoClient.getPointsOfInterestIds(continentId, floorId, regionId, mapId)

            // then
            result shouldHaveSize 51
        }

        should("Ghould get sectors") {
            // given
            val continentId = 1
            val floorId = 1
            val regionId = 1
            val mapId = 26

            stubResponse(
                "/v2/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/sectors",
                "/responses/mapinfo/sectors.json"
            )

            // when
            val result = mapInfoClient.getSectors(continentId, floorId, regionId, mapId)

            // then
            result shouldHaveSize 28
        }

        should("Ghould convert Dimensions to and from float pairs") {
            // given
            val pair = Pair(100f, 200f)

            // when
            val dimensions = Dimensions.fromPair(pair)

            // then
            assertSoftly {
                dimensions.x shouldBe pair.first
                dimensions.y shouldBe pair.second
                dimensions.asPair() shouldBe pair
            }
        }

        should("Ghould convert Rectangle to and from dimension pairs") {
            // given
            val pair = Pair(
                Dimensions(200f, 100f),
                Dimensions(300f, 400f)
            )

            // when
            val rectangle = Rectangle.fromPair(pair)

            // then
            assertSoftly {
                rectangle.first.x shouldBe pair.first.x
                rectangle.first.y shouldBe pair.first.y
                rectangle.second.x shouldBe pair.second.x
                rectangle.second.y shouldBe pair.second.y
                rectangle.asPair() shouldBe pair
            }
        }
    }
}