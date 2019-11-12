package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class WvwClientSpec extends WiremockConfig {

    @Subject
    def wvwClient = new GWWvwClient()

    def "Should get ability ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/abilities", "/responses/wvw/ability_ids.json")

        when: "Requesting ability ids"
        def ids = wvwClient.getAbilityIds()

        then: "Retrieved ids match expected"
        ids.size() == 22
    }

    def "Should get ability"() {
        given: "External api is stubbed"
        stubResponse("/wvw/abilities?ids=2&lang=en", "/responses/wvw/ability.json")

        when: "Requesting ability"
        def abilities = wvwClient.getAbilities([2], "en")

        then: "Retrieved ability matches expected"
        verifyAll(abilities.get(0)) {
            id == 2
            name == "Guard Killer"
            description == "Increases damage to enemy guards"
            icon == "https://render.guildwars2.com/file/C5E6E906927E2C6311036C11F1306CCF57CBF259/544537.png"
            verifyAll(ranks.get(0)) {
                cost == 1
                effect == "4% damage to guards"
            }
        }
    }

    def "Should get match ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/matches", "/responses/wvw/match_ids.json")

        when: "Requesting wvw match ids"
        def ids = wvwClient.getMatchIds()

        then: "Retrieved list matches expected"
        ids == [
                "2-1",
                "2-2",
                "2-3",
                "2-4",
                "2-5",
                "1-1",
                "1-2",
                "1-3",
                "1-4"
        ]
    }

    def "Should get match"() {
        given: "External api is stubbed"
        stubResponse("/wvw/matches?ids=1-3", "/responses/wvw/match.json")

        when: "Requesting matches"
        def matches = wvwClient.getMatches(["1-3"])

        then: "Retrieved matches match expected"
        verifyAll(matches.get(0)) {
            id == "1-3"
            startTime == "2019-11-09T02:00:00Z"
            endTime == "2019-11-16T01:58:00Z"
            verifyAll(scores) {
                red == 63163
                blue == 132823
                green == 68400
            }
            verifyAll(skirmishes.get(0)) {
                id == 1
                verifyAll(mapScores.get(0)) {
                    type == "Center"
                }
            }
            verifyAll(maps.get(0)) {
                id == 38
                type == "Center"
                bonuses == []
                verifyAll(objectives.get(0)) {
                    id == "38-131"
                    it.type == "Spawn"
                    it.owner == "Green"
                    lastFlipped == "2019-11-09T02:03:50Z"
                    pointsTick == 0
                    pointsCapture == 0
                }
            }
        }
    }

    def "Should get match overview"() {
        given: "External api is stubbed"
        stubResponse("/wvw/matches/overview?world=1008", "/responses/wvw/overview.json")

        when: "Requesting match overview"
        def overview = wvwClient.getMatchesOverview(1008)

        then: "Retrieved overview matches expected"
        verifyAll(overview) {
            id == "1-1"
            startTime == "2019-11-09T02:00:00Z"
            endTime == "2019-11-16T01:58:00Z"
            worlds != null
            allWorlds != null
        }
    }

    def "Should get match scores"() {
        given: "External api is stubbed"
        stubResponse("/wvw/matches/scores?world=1008", "/responses/wvw/scores.json")

        when: "Requesting match scores"
        def scores = wvwClient.getMatchScores(1008)

        then: "Retrieved scores match expected"
        verifyAll(scores) {
            id == "1-1"
            scores != null
            victoryPoints != null
            skirmishes != null
            maps != null
        }
    }

    def "Should get match stats"() {
        given: "External api is stubbed"
        stubResponse("/wvw/matches/stats?world=1008", "/responses/wvw/stats.json")

        when: "Requesting match stats"
        def stats = wvwClient.getMatchStats(1008)

        then: "Retrieved stats match expected"
        verifyAll(stats) {
            id == "1-1"
            deaths != null
            kills != null
            maps != null
        }
    }

    def "Should get objective ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/objectives", "/responses/wvw/objective_ids.json")

        when: "Requesting objective ids"
        def ids = wvwClient.getObjectiveIds()

        then: "Retrieved list matches expected"
        ids.size() == 178
    }

    def "Should get objective"() {
        given: "External api is stubbed"
        stubResponse("/wvw/objectives?ids=38-6&lang=en", "/responses/wvw/objective.json")

        when: "Requesting objective"
        def objectives = wvwClient.getObjectives(["38-6"], "en")

        then: "Retrieved objective match expected"
        verifyAll(objectives.get(0)) {
            id == "38-6"
            name == "Speldan Clearcut"
            sectorId == 844
            type == "Camp"
            mapType == "Center"
            mapId == 38
            upgradeId == 22
            coord == [9841.05d, 13545.8d, -508.295d]
            labelCoord == [9730.23d, 13640.4d]
            marker == "https://render.guildwars2.com/file/015D365A08AAE105287A100AAE04529FDAE14155/102532.png"
            chatLink == "[&DAYAAAAmAAAA]"
        }
    }

    def "Should get rank ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/ranks", "/responses/wvw/rank_ids.json")

        when: "Requesting rank ids"
        def ids = wvwClient.getRankIds()

        then: "Retrieved list matches expected"
        ids.size() == 105
    }

    def "Should get rank"() {
        given: "External api is stubbed"
        stubResponse("/wvw/ranks?ids=1&lang=en", "/responses/wvw/rank.json")

        when: "Requesting rank"
        def ranks = wvwClient.getRanks([1], "en")

        then: "Retrieved rank matches expected"
        verifyAll(ranks.get(0)) {
            id == 1
            title == "Invader"
            minRank == 1
        }
    }

    def "Should get upgrade ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/upgrades", "/responses/wvw/upgrade_ids.json")

        when: "Requesting upgrade ids"
        def ids = wvwClient.getUpgradeIds()

        then: "Retrieved list matches expected"
        ids.size() == 48
    }

    def "Should get upgrade"() {
        given: "External api is stubbed"
        stubResponse("/wvw/upgrades?ids=2&lang=en", "/responses/wvw/upgrade.json")

        when: "Requesting upgrade"
        def upgrades = wvwClient.getUpgrades([2], "en")

        then: "Retrieved upgrade matches expected"
        verifyAll(upgrades.get(0)) {
            id == 2
            verifyAll(tiers.get(0)) {
                name == "Secured"
                yaksRequired == 20
                verifyAll(it.upgrades.get(0)) {
                    name == "Caravan Guards"
                    description == "Recruits guards to escort the camp's dolyak caravans."
                    icon == "https://render.guildwars2.com/file/A81310D55E7BEA075E2F739A43E223070414EFE3/105225.png"
                }
            }
        }
    }
}
