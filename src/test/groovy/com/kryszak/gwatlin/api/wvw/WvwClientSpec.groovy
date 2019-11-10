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
}
