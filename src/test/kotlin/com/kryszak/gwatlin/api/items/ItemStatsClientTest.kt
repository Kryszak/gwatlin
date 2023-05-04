package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class ItemStatsClientTest : BaseWiremockTest() {

    private val itemStatsClient = GWItemStatsClient()

    init {
        should("Get item stats ids") {
            // given
            stubResponse("/itemstats", "/responses/items/item_stats_ids.json")

            // when
            val statsIds = itemStatsClient.getItemStatsIds()

            // then
            statsIds shouldHaveSize 177
        }

        should("Get item stats") {
            // given
            val id = 584
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/itemstats?ids=584", "/responses/items/item_stats.json", lang)

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
    }
}