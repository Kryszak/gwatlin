package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class DungeonsClientTest : BaseWiremockTest() {

    private val dungeonsClient = GWDungeonsClient()

    init {
        should("Get all dungeons") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/dungeons?ids=all", "/responses/gamemechanics/dungeons/dungeons_all.json", language = lang)

            // when
            val dungeons = dungeonsClient.getAllDungeons(lang)

            // then
            assertSoftly(dungeons[0]) {
                id shouldBe "ascalonian_catacombs"
                assertSoftly(paths[0]) {
                    id shouldBe "ac_story"
                    type shouldBe "Story"
                }
            }
        }
        should("Get dungeon ids") {
            // given
            stubResponse("/v2/dungeons", "/responses/gamemechanics/dungeons/dungeon_ids.json")

            // when
            val dungeonIds = dungeonsClient.getDungeonIds()

            // then
            dungeonIds shouldHaveSize 8
        }
        should("Get dungeon") {
            // given
            val dungeonId = "ascalonian_catacombs"
            stubResponse("/v2/dungeons/$dungeonId", "/responses/gamemechanics/dungeons/dungeon.json")

            // when
            val dungeon = dungeonsClient.getDungeon(dungeonId)

            // then
            assertSoftly(dungeon) {
                id shouldBe dungeonId
                paths shouldHaveSize 4
                assertSoftly(paths[0]) {
                    id shouldBe "ac_story"
                    type shouldBe "Story"
                }
            }
        }
        should("Get dungeons") {
            // given
            val dungeonIds = listOf("caudecus_manor", "twilight_arbor")
            stubResponse("/v2/dungeons?ids=${dungeonIds.joinToString(",")}", "/responses/gamemechanics/dungeons/dungeons.json")

            // when
            val dungeons = dungeonsClient.getDungeons(dungeonIds)

            // then
            assertSoftly(dungeons) {
                it shouldHaveSize 2
                assertSoftly(dungeons[0]) {
                    id shouldBe dungeonIds[0]
                    paths shouldHaveSize 4
                    assertSoftly(paths[0]) {
                        id shouldBe "cm_story"
                        type shouldBe "Story"
                    }
                }
                assertSoftly(dungeons[1]) {
                    id shouldBe dungeonIds[1]
                    paths shouldHaveSize 4
                    assertSoftly(paths[0]) {
                        id shouldBe "ta_story"
                        type shouldBe "Story"
                    }
                }
            }
        }
    }
}
