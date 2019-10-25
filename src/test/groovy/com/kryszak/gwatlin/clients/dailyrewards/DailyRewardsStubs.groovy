package com.kryszak.gwatlin.clients.dailyrewards

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.*

class DailyRewardsStubs extends WiremockConfig {

    def stubDailyCraftingRecipesResponse() {
        stubFor(
                get(urlEqualTo("/dailycrafting"))
                        .willReturn(okJson(parseResponseText("/responses/dailyrewards/crafting_recipes_ids.json")))
        )
    }

    def stubMapChestsResponse() {
        stubFor(
                get(urlEqualTo("/mapchests"))
                        .willReturn(okJson(parseResponseText("/responses/dailyrewards/map_chests.json")))
        )
    }

    def stubWorldBossesResponse() {
        stubFor(
                get(urlEqualTo("/worldbosses"))
                        .willReturn(okJson(parseResponseText("/responses/dailyrewards/world_bosses.json")))
        )
    }
}
