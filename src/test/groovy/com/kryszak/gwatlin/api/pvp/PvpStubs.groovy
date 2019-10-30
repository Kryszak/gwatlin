package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

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
}
