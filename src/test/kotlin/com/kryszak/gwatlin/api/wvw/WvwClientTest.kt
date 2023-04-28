package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTestKt
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class WvwClientTest : WiremockTestKt() {

    private val wvwClient = GWWvwClient()

    init {
        should("Get ability ids") {
            // given
            stubResponse("/wvw/abilities", "/responses/wvw/ability_ids.json")

            // when
            val ids = wvwClient.getAbilityIds()

            // then
            ids shouldContainExactly listOf(
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                23,
                24,
                25,
                26
            )
        }

        should("Get ability") {
            // given
            stubResponse("/wvw/abilities?ids=2", "/responses/wvw/ability.json")

            // when
            val abilities = wvwClient.getAbilities(listOf(2))

            // then
            assertSoftly(abilities[0]) {
                id shouldBe 2
                name shouldBe "Guard Killer"
                description shouldBe "Increases damage to enemy guards"
                icon shouldBe "https://render.guildwars2.com/file/C5E6E906927E2C6311036C11F1306CCF57CBF259/544537.png"
                assertSoftly(ranks[0]) {
                    cost shouldBe 1
                    effect shouldBe "4% damage to guards"
                }
            }
        }

        should("Get match ids") {
            // given
            stubResponse("/wvw/matches", "/responses/wvw/match_ids.json")

            // when
            val ids = wvwClient.getMatchIds()

            // then
            ids shouldContainExactly listOf(
                "2-1",
                "2-2",
                "2-3",
                "2-4",
                "2-5",
                "1-1",
                "1-2",
                "1-3",
                "1-4"
            )
        }

        should("Get match") {
            // given
            stubResponse("/wvw/matches?ids=1-3", "/responses/wvw/match.json")

            // when
            val matches = wvwClient.getMatches(listOf("1-3"))

            // then
            assertSoftly(matches[0]) {
                id shouldBe "1-3"
                startTime shouldBe "2019-11-09T02:00:00Z"
                endTime shouldBe "2019-11-16T01:58:00Z"
                assertSoftly(scores) {
                    red shouldBe 63163
                    blue shouldBe 132823
                    green shouldBe 68400
                }
                assertSoftly(skirmishes[0]) {
                    id shouldBe 1
                    assertSoftly(mapScores[0]) {
                        type shouldBe "Center"
                    }
                }
                assertSoftly(maps[0]) {
                    id shouldBe 38
                    type shouldBe "Center"
                    bonuses shouldBe listOf()
                    assertSoftly(objectives[0]) {
                        id shouldBe "38-131"
                        it.type shouldBe "Spawn"
                        it.owner shouldBe "Green"
                        lastFlipped shouldBe "2019-11-09T02:03:50Z"
                        pointsTick shouldBe 0
                        pointsCapture shouldBe 0
                    }
                }
            }
        }

        should("Get match overview") {
            // given
            stubResponse("/wvw/matches/overview?world=1008", "/responses/wvw/overview.json")

            // when
            val overview = wvwClient.getMatchesOverview(1008)

            // then
            assertSoftly(overview) {
                id shouldBe "1-1"
                startTime shouldBe "2019-11-09T02:00:00Z"
                endTime shouldBe "2019-11-16T01:58:00Z"
                worlds shouldNotBe null
                allWorlds shouldNotBe null
            }
        }

        should("Get match scores") {
            // given
            stubResponse("/wvw/matches/scores?world=1008", "/responses/wvw/scores.json")

            // when
            val scores = wvwClient.getMatchScores(1008)

            // then
            assertSoftly(scores) {
                id shouldBe "1-1"
                scores shouldNotBe null
                victoryPoints shouldNotBe null
                skirmishes shouldNotBe null
                maps shouldNotBe null
            }
        }

        should("Get match stats") {
            // given
            stubResponse("/wvw/matches/stats?world=1008", "/responses/wvw/stats.json")

            // when
            val stats = wvwClient.getMatchStats(1008)

            // then
            assertSoftly(stats) {
                id shouldBe "1-1"
                deaths shouldNotBe null
                kills shouldNotBe null
                maps shouldNotBe null
            }
        }

        should("Get objective ids") {
            // given
            stubResponse("/wvw/objectives", "/responses/wvw/objective_ids.json")

            // when
            val ids = wvwClient.getObjectiveIds()

            // then
            ids.size shouldBe 178
        }

        should("Get objective") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/wvw/objectives?ids=38-6", "/responses/wvw/objective.json", lang)

            // when
            val objectives = wvwClient.getObjectives(listOf("38-6"), lang)

            // then
            assertSoftly(objectives.get(0)) {
                id shouldBe "38-6"
                name shouldBe "Speldan Clearcut"
                sectorId shouldBe 844
                type shouldBe "Camp"
                mapType shouldBe "Center"
                mapId shouldBe 38
                upgradeId shouldBe 22
                coord shouldBe listOf(9841.05, 13545.8, -508.295)
                labelCoord shouldBe listOf(9730.23, 13640.4)
                marker shouldBe "https://render.guildwars2.com/file/015D365A08AAE105287A100AAE04529FDAE14155/102532.png"
                chatLink shouldBe "[&DAYAAAAmAAAA]"
            }
        }

        should("Get rank ids") {
            // given
            stubResponse("/wvw/ranks", "/responses/wvw/rank_ids.json")

            // when
            val ids = wvwClient.getRankIds()

            // then
            ids.size shouldBe 105
        }

        should("Get rank") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/wvw/ranks?ids=1", "/responses/wvw/rank.json", lang)

            // when
            val ranks = wvwClient.getRanks(listOf(1), lang)

            // then
            assertSoftly(ranks[0]) {
                id shouldBe 1
                title shouldBe "Invader"
                minRank shouldBe 1
            }
        }

        should("Get upgrade ids") {
            // given
            stubResponse("/wvw/upgrades", "/responses/wvw/upgrade_ids.json")

            // when
            val ids = wvwClient.getUpgradeIds()

            // then
            ids.size shouldBe 48
        }

        should("Get upgrade") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/wvw/upgrades?ids=2", "/responses/wvw/upgrade.json", lang)

            // when
            val upgrades = wvwClient.getUpgrades(listOf(2), lang)

            // then
            assertSoftly(upgrades[0]) {
                id shouldBe 2
                assertSoftly(tiers[0]) {
                    name shouldBe "Secured"
                    yaksRequired shouldBe 20
                    assertSoftly(it.upgrades[0]) {
                        name shouldBe "Caravan Guards"
                        description shouldBe "Recruits guards to escort the camp's dolyak caravans."
                        icon shouldBe "https://render.guildwars2.com/file/A81310D55E7BEA075E2F739A43E223070414EFE3/105225.png"
                    }
                }
            }
        }
    }
}