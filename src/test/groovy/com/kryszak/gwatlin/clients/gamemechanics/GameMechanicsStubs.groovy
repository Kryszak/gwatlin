package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.*

class GameMechanicsStubs extends WiremockConfig {

    def stubMasteriesIdsResponse() {
        stubFor(
                get(urlEqualTo("/masteries"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/masteries_ids.json")))
        )
    }

    def stubMasteryResponse() {
        stubFor(
                get(urlEqualTo("/masteries/1?lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mastery.json")))
        )
    }

    def stubMasteryFrenchResponse() {
        stubFor(
                get(urlEqualTo("/masteries/1?lang=fr"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mastery_fr.json")))
        )
    }

    def stubMasteryErrorResponse() {
        stubFor(
                get(urlEqualTo("/masteries/40?lang=en"))
                        .willReturn(badRequest()
                                .withBody(parseResponseText("/responses/gamemechanics/mastery_error.json")))
        )
    }

    def stubMasteriesResponse() {
        stubFor(
                get(urlEqualTo("/masteries?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/masteries.json")))
        )
    }
}
