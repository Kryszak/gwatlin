package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.*

class PvpStubs extends WiremockConfig {

    def stubPvpRankIdsResponse() {
        stubFor(
                get(urlEqualTo("/pvp/ranks"))
                        .willReturn(okJson(parseResponseText("/responses/pvp/rank_ids,json")))
        )
    }

    def stubPvpRanksResponse() {
        stubFor(
                get(urlEqualTo("/pvp/ranks?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/pvp/ranks.json")))
        )
    }

    def stubPvpSeasonsIdsResponse() {
        stubFor(
                get(urlEqualTo("/pvp/seasons"))
                        .willReturn(okJson(parseResponseText("/responses/pvp/season_ids.json")))
        )
    }

    def stubPvpSeasonsResponse() {
        stubFor(
                get(urlEqualTo("/pvp/seasons?ids=44B85826-B5ED-4890-8C77-82DDF9F2CF2B,95D5B290-798A-421E-A919-1C2A75F74B72&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/pvp/seasons.json")))
        )
    }
}
