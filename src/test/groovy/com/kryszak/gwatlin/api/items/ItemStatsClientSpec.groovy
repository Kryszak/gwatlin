package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest

class ItemStatsClientSpec extends WiremockTest {

    def itemStatsClient = new GWItemStatsClient()

    def "Should get item stats ids"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/items/item_stats_ids.json")

        and: "External api is stubbed"
        stubResponse("/itemstats", "/responses/items/item_stats_ids.json")

        when: "Requesting item stats ids list"
        def statsIds = itemStatsClient.getItemStatsIds()

        then: "Retrieved list matches expected"
        statsIds == ids
    }

    def "Should get item stats"() {
        given: "Item stat id"
        def id = 584

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/itemstats?ids=584", "/responses/items/item_stats.json", lang)

        when: "Requesting item stats"
        def stats = itemStatsClient.getItemStats([id], lang)

        then: "Retrieved statistics matches expected"
        verifyAll(stats.get(0)) {
            id == 584
            name == "Berserker's"
            verifyAll(attributes.get(0)) {
                attribute == "Power"
                multiplier == 0.35d
                value == 32
            }
        }
    }
}
