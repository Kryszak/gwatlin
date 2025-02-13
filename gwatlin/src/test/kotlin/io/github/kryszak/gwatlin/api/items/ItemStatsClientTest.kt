package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class ItemStatsClientTest : BaseWiremockTest() {

    private val itemStatsClient = GWItemStatsClient()

    init {
        should("Get item stats ids") {
            // given
            stubResponse("/v2/itemstats", "/responses/items/item_stats_ids.json")

            // when
            val statsIds = itemStatsClient.getItemStatsIds()

            // then
            statsIds shouldHaveSize 177
        }

        should("Get item stats") {
            // given
            val id = 584
            val lang = ApiLanguage.EN

            stubResponse("/v2/itemstats?ids=584", "/responses/items/item_stats.json", language = lang)

            // when
            val stats = itemStatsClient.getItemStats(listOf(id), lang)

            // then
            assertSoftly(stats[0]) {
                id shouldBe 584
                name shouldBe "Berserker's"
                assertSoftly(attributes[0]) {
                    attribute shouldBe "Power"
                    multiplier shouldBe 0.35
                    value shouldBe 32
                }
            }
        }

        should("Get paged item stats") {
            // given
            stubResponse(
                "/v2/itemstats?page=0&page_size=10",
                "/responses/items/item_stats_paged.json",
                pageParams = PageParameters(10, 20, 10, 191)
            )

            // when
            val pagedItemStats = itemStatsClient.getPagedItemStats(PageRequest(0, 10))

            // then
            assertSoftly(pagedItemStats) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 20
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 191
            }
        }
    }
}