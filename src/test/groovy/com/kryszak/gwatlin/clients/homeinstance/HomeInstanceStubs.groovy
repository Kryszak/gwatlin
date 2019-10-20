package com.kryszak.gwatlin.clients.homeinstance

import com.kryszak.gwatlin.config.WiremockConfig
import static com.github.tomakehurst.wiremock.client.WireMock.*

class HomeInstanceStubs extends WiremockConfig {

    def stubCatIdsResponse() {
        stubFor(
                get(urlEqualTo("/home/cats"))
                .willReturn(okJson(parseResponseText("/responses/homeinstance/cat_ids.json")))
        )
    }

    def stubCatResponse() {
        stubFor(
                get(urlEqualTo("/home/cats/1"))
                .willReturn(okJson(parseResponseText("/responses/homeinstance/cat.json")))
        )
    }

    def stubErrorCatResponse() {
        stubFor(
                get(urlEqualTo("/home/cats/100"))
                .willReturn(notFound().withBody(parseResponseText("/responses/homeinstance/cat_error.json")))
        )
    }

    def stubCatsResponse() {
        stubFor(
                get(urlEqualTo("/home/cats?ids=1,2,3"))
                .willReturn(okJson(parseResponseText("/responses/homeinstance/cats.json")))

        )
    }

    def stubNodeIdsResponse() {
        stubFor(
                get(urlEqualTo("/home/nodes"))
                .willReturn(okJson(parseResponseText("/responses/homeinstance/node_ids.json")))
        )
    }

    def stubNodeResponse() {
        stubFor(
                get(urlEqualTo("/home/nodes/basic_lumber_nodes"))
                .willReturn(okJson(parseResponseText("/responses/homeinstance/node.json")))
        )
    }

    def stubNodeErrorResponse() {
        stubFor(
                get(urlEqualTo("/home/nodes/i_do_not_exist"))
                .willReturn(notFound().withBody(parseResponseText("/responses/homeinstance/node_error.json")))
        )
    }
}
