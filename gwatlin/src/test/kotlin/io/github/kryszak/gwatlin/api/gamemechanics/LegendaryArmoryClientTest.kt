package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class LegendaryArmoryClientTest : BaseWiremockTest() {

    private val client = GWLegendaryArmoryClient()

    init {
        should("Get legendary armory item ids") {
            // given
            stubResponse("/v2/legendaryarmory", "/responses/gamemechanics/legendaryarmory/legendary_armory_ids.json")

            // when
            val ids = client.getLegendaryArmoryIds()

            // then
            ids shouldHaveSize 192
        }

        should("Get legendary armory item") {
            // given
            val legendaryArmoryId = 81462L
            stubResponse(
                "/v2/legendaryarmory/$legendaryArmoryId",
                "/responses/gamemechanics/legendaryarmory/legendary_armory_item.json"
            )

            // when
            val legendaryArmoryItem = client.getLegendaryArmoryItem(legendaryArmoryId)

            // then
            assertSoftly(legendaryArmoryItem) {
                id shouldBe legendaryArmoryId
                maxCount shouldBe 1
            }
        }

        should("Get legendary armory items") {
            // given
            val legendaryArmoryIds = listOf(81462L, 74155L)
            stubResponse(
                "/v2/legendaryarmory?ids=${legendaryArmoryIds.joinToString(",")}",
                "/responses/gamemechanics/legendaryarmory/legendary_armory_items.json"
            )

            // when
            val legendaryArmoryItems = client.getLegendaryArmoryItems(legendaryArmoryIds)

            // then
            assertSoftly(legendaryArmoryItems) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe legendaryArmoryIds[0]
                    maxCount shouldBe 1
                }
                assertSoftly(it[1]) {
                    id shouldBe legendaryArmoryIds[1]
                    maxCount shouldBe 1
                }
            }
        }

        should("Get all legendary armory items") {
            // given
            stubResponse(
                "/v2/legendaryarmory?ids=all",
                "/responses/gamemechanics/legendaryarmory/legendary_armory_all.json"
            )

            // when
            val legendaryArmoryItems = client.getAllLegendaryArmoryItems()

            // then
            legendaryArmoryItems shouldHaveSize 192
        }

        should("Get paged legendary armory items") {
            // given
            stubResponse(
                "/v2/legendaryarmory?page=0&page_size=10",
                "/responses/gamemechanics/legendaryarmory/legendary_armory_paged.json",
                pageParams = PageParameters(10, 20, 10, 192)
            )

            // when
            val pagedLegendaryArmoryItems = client.getPagedLegendaryArmoryItems(PageRequest(0, 10))

            // then
            assertSoftly(pagedLegendaryArmoryItems) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 20
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 192
            }
        }
    }
}
