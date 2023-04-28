package com.kryszak.gwatlin.api.mapinfo

import com.kryszak.gwatlin.api.mapinfo.model.Adventure
import com.kryszak.gwatlin.api.mapinfo.model.Continent
import com.kryszak.gwatlin.api.mapinfo.model.ContinentMap
import com.kryszak.gwatlin.api.mapinfo.model.Dimensions
import com.kryszak.gwatlin.api.mapinfo.model.Floor
import com.kryszak.gwatlin.api.mapinfo.model.HeartTask
import com.kryszak.gwatlin.api.mapinfo.model.Map
import com.kryszak.gwatlin.api.mapinfo.model.MapType
import com.kryszak.gwatlin.api.mapinfo.model.MasteryPoint
import com.kryszak.gwatlin.api.mapinfo.model.PointOfInterest
import com.kryszak.gwatlin.api.mapinfo.model.PointOfInterestType
import com.kryszak.gwatlin.api.mapinfo.model.Rectangle
import com.kryszak.gwatlin.api.mapinfo.model.Region
import com.kryszak.gwatlin.api.mapinfo.model.Sector
import com.kryszak.gwatlin.api.mapinfo.model.SkillChallenge
import com.kryszak.gwatlin.config.WiremockSpec
import kotlin.Pair
import spock.lang.Subject

class MapInfoClientSpec extends WiremockSpec {

    final TARGET_SCHEMA_VERSION = "2022-03-23T19:00:00.000Z"

    @Subject
    final mapInfoClient = new GWMapInfoClient()

    def "Should get continent map"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 1

        and: "Region ID"
        def regionId = 1

        and: "Map ID"
        def mapId = 26

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId",
                "/responses/mapinfo/continentMap.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting map"
        def result = mapInfoClient.getMap(continentId, floorId, regionId, mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            // Test constructor
            def constructed = new ContinentMap(id, name, minLevel, maxLevel, defaultFloor, mapRect, continentRect, labelCoord,
            pointsOfInterest, tasks, skillChallenges, sectors, adventures, masteryPoints)
            it == constructed

            name == "Dredgehaunt Cliffs"
            id == 26
            minLevel == 40
            maxLevel == 50
            defaultFloor == 1

            // Floating point comparison usually, so we will just cast them to int for testing purposes
            labelCoord.x.intValue() == 53376
            labelCoord.y.intValue() == 32960

            mapRect.first.x.intValue() == -27648
            mapRect.second.y.intValue() == 39936

            continentRect.first.x.intValue() == 52224
            continentRect.second.y.intValue() == 34560

            pointsOfInterest.size() == 51
            def poi = pointsOfInterest.get(603)
            // Test constructor
            def poiConstructed = new PointOfInterest(poi.id, poi.name, poi.type, poi.floor,
                    poi.coord, poi.chatLink, poi.icon)
            poi == poiConstructed

            poi.name == "Steelbrachen Waypoint"
            poi.id == 603
            poi.type == PointOfInterestType.WAYPOINT
            poi.floor == 1
            poi.chatLink == "[&BFsCAAA=]"
            poi.coord.y.intValue() == 33489

            def poi2 = pointsOfInterest.get(1732)
            poi2.type == PointOfInterestType.UNLOCK
            poi2.icon == "https://render.guildwars2.com/file/943538394A94A491C8632FBEF6203C2013443555/102478.png"

//            labelCoord == new Dimensions(53376, 32960)
//            mapRect == new Rectangle(
//                    new Dimensions(-27648, -36864),
//                    new Dimensions(27648, 39936),
//            )


            tasks.size() == 11
            def task = tasks.get(5)
            // Test constructor
            def taskConstructed = new HeartTask(task.id, task.objective, task.level, task.coord, task.bounds, task.chatLink)
            task == taskConstructed

            task.level == 47
            task.coord.x.intValue() == 54217.1.intValue()
            task.coord.y.intValue() == 33882.8.intValue()
            task.id == 5
            task.chatLink == "[&BAUAAAA=]"
            task.bounds.size() == 18
            task.bounds.get(2).x.intValue() == 54255.6.intValue()
            task.objective == "Help Arcanist Vance study dwarven relics at Granite Citadel."

            skillChallenges.size() == 6
            def hp = skillChallenges.get(4)
            // Test constructor
            def hpConstructed = new SkillChallenge(hp.id, hp.coord)
            hp == hpConstructed

            hp.id == "0-58"
            hp.coord.x.intValue() == 53436.7.intValue()

            sectors.size() == 28
            def sec = sectors.get(517)
            // Test constructor
            def secConstructed = new Sector(sec.id, sec.name, sec.level, sec.coord, sec.bounds, sec.chatLink)
            sec == secConstructed

            sec.level == 49
            sec.name == "Kapellenburg"
            sec.bounds.size() == 8
            sec.id == 517
            sec.chatLink == "[&BAUCAAA=]"
            sec.coord.y.intValue() == 33705.1.intValue()

            adventures.size() == 2
            def adv = adventures.first()
            // Test constructor
            def advConstructed = new Adventure(adv.id, adv.coord, adv.name, adv.description)
            adv == advConstructed

            adv.coord.x.intValue() == 37316.5.intValue()
            adv.id == "8D00FA87-28CD-4402-AAEF-501A610E0447"
            adv.name == "Beetle Feast"
            adv.description == "Eat yellow mushrooms to score points and blue mushrooms to gain abilities that help you in your hunt. Beware of the poisonous mushrooms with green spores, and use your burrow ability to bypass closed gates!"

            masteryPoints.size() == 1
            def mp = masteryPoints.first()
            // Test constructor
            def mpConstructed = new MasteryPoint(mp.id, mp.coord, mp.region)
            mp == mpConstructed

            mp.id == 319
            mp.coord.x.intValue() == 53190.8.intValue()
            mp.region.toLowerCase() == "tyria"
        }
    }

    def "Should get continents"() {
        given: "External API is stubbed"
        stubResponseWithSchema(
                "/continents",
                "/responses/mapinfo/continents.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting continents"
        def result = mapInfoClient.getContinents()

        then: "Retrieved details matches expected"
        verifyAll {
            result == [1, 2]
        }
    }

    def "Should get continent"() {
        given: "Continent ID"
        def continentId = 1

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId",
                "/responses/mapinfo/continentOne.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting continent"
        def result = mapInfoClient.getContinent(continentId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            // Test constructor
            def constructed = new Continent(id, name, continentDims, minZoom, maxZoom, floors)
            it == constructed

            id == 1
            name == "Tyria"
            continentDims.x.intValue() == 81920
            continentDims.y.intValue() == 114688
            minZoom == 0
            maxZoom == 8
            floors.size() == 119
        }
    }

    def "Should get floors"() {
        given: "Continent ID"
        def continentId = 1

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors",
                "/responses/mapinfo/continentOneFloors.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting floors"
        def result = mapInfoClient.getFloors(continentId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 119
        }
    }

    def "Should get floor"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 6

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId",
                "/responses/mapinfo/continentOneFloor6.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting floor"
        def result = mapInfoClient.getFloor(continentId, floorId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            // Test constructor
            def constructed = new Floor(textureDims, clampedView, regions)
            it == constructed

            textureDims.x.intValue() == 81920
            textureDims.y.intValue() == 114688

            clampedView.first.x.intValue() == 54272
            clampedView.second.y.intValue() == 25856

            regions.size() == 1
        }
    }

    def "Should get regions"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 6

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions",
                "/responses/mapinfo/continentOneFloor6Regions.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting regions"
        def result = mapInfoClient.getRegions(continentId, floorId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            it == [1]
        }
    }

    def "Should get region"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 6

        and: "Region ID"
        def regionId = 6

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId",
                "/responses/mapinfo/continentOneFloor6Region1.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting region"
        def result = mapInfoClient.getRegion(continentId, floorId, regionId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            // Test constructor
            def constructed = new Region(id, name, labelCoord, continentRect, maps)
            it == constructed

            name == "Shiverpeak Mountains"
            id == 1
            labelCoord.x.intValue() == 52608
            continentRect.first.x.intValue() == 50432
            continentRect.second.y.intValue() == 37760
            maps.size() == 2
        }
    }

    def "Should get continent maps"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 6

        and: "Region ID"
        def regionId = 6

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps",
                "/responses/mapinfo/continentOneFloor6Region1Maps.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting continent maps"
        def result = mapInfoClient.getMaps(continentId, floorId, regionId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            it == [70, 71]
        }
    }

    def "Should get all maps"() {
        given: "External API is stubbed"
        stubResponseWithSchema(
                "/maps",
                "/responses/mapinfo/maps.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting maps"
        def result = mapInfoClient.getMaps()

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 937
        }
    }

    def "Should get multiple maps"() {
        given: "Map IDs"
        def ids = [26, 27]

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/maps?ids=${ids.join(",")}",
                "/responses/mapinfo/mapsMultiple.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting maps"
        def result = mapInfoClient.getMaps(ids)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 2
        }
    }

    def "Should get map"() {
        given: "Map ID"
        def mapId = 26

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/maps/$mapId",
                "/responses/mapinfo/map26.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting map"
        def result = mapInfoClient.getMap(mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            // Test Constructor
            def constructed = new Map(id, name, minLevel, maxLevel, defaultFloor, type, floors, regionId, regionName,
            continentId, continentName, mapRect, continentRect)
            it == constructed

            id == 26
            name == "Dredgehaunt Cliffs"
            minLevel == 40
            maxLevel == 50
            defaultFloor == 1
            type == MapType.PUBLIC
            floors == [1, 3, 2, 0]
            regionId == 1
            regionName == "Shiverpeak Mountains"
            continentId == 1
            continentName == "Tyria"
            mapRect.first.x.intValue() == -27648
            continentRect.first.x.intValue() == 52224
        }
    }

    def "Should get tasks"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 1

        and: "Region ID"
        def regionId = 1

        and: "Map ID"
        def mapId = 26

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/tasks",
                "/responses/mapinfo/tasks.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting tasks"
        def result = mapInfoClient.getTasks(continentId, floorId, regionId, mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 11
        }
    }

    def "Should get POIs"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 1

        and: "Region ID"
        def regionId = 1

        and: "Map ID"
        def mapId = 26

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/pois",
                "/responses/mapinfo/pois.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting tasks"
        def result = mapInfoClient.getPointsOfInterest(continentId, floorId, regionId, mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 51
        }
    }

    def "Should get sectors"() {
        given: "Continent ID"
        def continentId = 1

        and: "Floor ID"
        def floorId = 1

        and: "Region ID"
        def regionId = 1

        and: "Map ID"
        def mapId = 26

        and: "External API is stubbed"
        stubResponseWithSchema(
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId/sectors",
                "/responses/mapinfo/sectors.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting tasks"
        def result = mapInfoClient.getSectors(continentId, floorId, regionId, mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
            size() == 28
        }
    }

    def "Should convert Dimensions to and from float pairs"() {
        given: "Float pair"
        def pair = new Pair(100f, 200f)

        when: "Creating Dimensions from float pair"
        def dimensions = Dimensions.fromPair(pair)

        then: "Values are copied correctly"
        verifyAll {
            dimensions.x.intValue() == pair.first.intValue()
            dimensions.y.intValue() == pair.second.intValue()
            dimensions.asPair() == pair
        }
    }

    def "Should convert Rectangle to and from dimension pairs"() {
        given: "Dimensions pair"
        def pair = new Pair(
                new Dimensions(200f, 100f),
                new Dimensions(300f, 400f)
        )

        when: "Creating Rectanlge from Dimensions pair"
        def rectangle = Rectangle.fromPair(pair)

        then: "Values are copied correctly"
        verifyAll {
            rectangle.first.x.intValue() == pair.first.x.intValue()
            rectangle.first.y.intValue() == pair.first.y.intValue()
            rectangle.second.x.intValue() == pair.second.x.intValue()
            rectangle.second.y.intValue() == pair.second.y.intValue()
            rectangle.asPair() == pair
        }
    }

}
