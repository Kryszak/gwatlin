package com.kryszak.gwatlin.api.mapinfo

import com.kryszak.gwatlin.api.mapinfo.model.PointOfInterestType
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class MapInfoClientSpec extends WiremockConfig {

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
                "/continents/$continentId/floors/$floorId/regions/$regionId/maps/$mapId?lang=en",
                "/responses/mapinfo/map.json",
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting tasks"
        def result = mapInfoClient.getMap(continentId, floorId, regionId, mapId)

        then: "Retrieved details matches expected"
        verifyAll(result) {
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
            hp.id == "0-58"
            hp.coord.x.intValue() == 53436.7.intValue()

            sectors.size() == 28
            def sec = sectors.get(517)
            sec.level == 49
            sec.name == "Kapellenburg"
            sec.bounds.size() == 8
            sec.id == 517
            sec.chatLink == "[&BAUCAAA=]"
            sec.coord.y.intValue() == 33705.1.intValue()

            adventures.size() == 2
            def adv = adventures.first()
            adv.coord.x.intValue() == 37316.5.intValue()
            adv.id == "8D00FA87-28CD-4402-AAEF-501A610E0447"
            adv.name == "Beetle Feast"
            adv.description == "Eat yellow mushrooms to score points and blue mushrooms to gain abilities that help you in your hunt. Beware of the poisonous mushrooms with green spores, and use your burrow ability to bypass closed gates!"

            masteryPoints.size() == 1
            def mp = masteryPoints.first()
            mp.id == 319
            mp.coord.x.intValue() == 53190.8.intValue()
            mp.region.toLowerCase() == "tyria"
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

}
