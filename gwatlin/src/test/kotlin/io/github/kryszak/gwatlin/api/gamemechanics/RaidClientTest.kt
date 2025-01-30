package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.raid.RaidWingEventType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class RaidClientTest : BaseWiremockTest() {

    private val raidClient = GWRaidsClient()

    init {
        should("Get all raids") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/raids?ids=all", "/responses/gamemechanics/raids/raids_all.json", language = lang)

            // when
            val raids = raidClient.getAllRaids(lang)

            // then
            assertSoftly(raids[0]) {
                id shouldBe "forsaken_thicket"
                assertSoftly(wings[0]) {
                    id shouldBe "spirit_vale"
                    assertSoftly(events[0]) {
                        id shouldBe "vale_guardian"
                        type shouldBe RaidWingEventType.BOSS
                    }
                }
            }
        }
        should("Get raid ids") {
            // given
            stubResponse("/v2/raids", "/responses/gamemechanics/raids/raid_ids.json")

            // when
            val raidIds = raidClient.getRaidIds()

            // then
            raidIds shouldHaveSize 6
        }
        should("Get raid") {
            // given
            val raidId = "forsaken_thicket"
            stubResponse("/v2/raids/$raidId", "responses/gamemechanics/raids/raid.json")

            // when
            val raid = raidClient.getRaid(raidId)

            // then
            assertSoftly(raid) {
                id shouldBe raidId
                wings shouldHaveSize 3
                assertSoftly(wings[0]) {
                    id shouldBe "spirit_vale"
                    events shouldHaveSize 4
                    assertSoftly(events[0]) {
                        id shouldBe "vale_guardian"
                        type shouldBe RaidWingEventType.BOSS
                    }
                    assertSoftly(events[1]) {
                        id shouldBe "spirit_woods"
                        type shouldBe RaidWingEventType.CHECKPOINT
                    }
                }
            }
        }
        should("Get raids") {
            // given
            val raidIds = listOf("bastion_of_the_penitent", "hall_of_chains")
            stubResponse("/v2/raids?ids=${raidIds.joinToString(",")}", "responses/gamemechanics/raids/raids.json")

            // when
            val raids = raidClient.getRaids(raidIds)

            // then
            assertSoftly(raids) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe raidIds[0]
                    wings shouldHaveSize 1
                    assertSoftly(wings[0]) {
                        id shouldBe "bastion_of_the_penitent"
                        events shouldHaveSize 4
                        assertSoftly(events[0]) {
                            id shouldBe "cairn"
                            type shouldBe RaidWingEventType.BOSS
                        }
                    }
                }
                assertSoftly(it[1]) {
                    id shouldBe raidIds[1]
                    wings shouldHaveSize 1
                    assertSoftly(wings[0]) {
                        id shouldBe "hall_of_chains"
                        events shouldHaveSize 4
                        assertSoftly(events[0]) {
                            id shouldBe "soulless_horror"
                            type shouldBe RaidWingEventType.BOSS
                        }
                    }
                }
            }
        }
    }
}
