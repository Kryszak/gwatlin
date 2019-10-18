package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.junit.WireMockRule
import groovy.json.JsonSlurper
import org.junit.Rule
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class WiremockConfig extends Specification {

    @Rule
    WireMockRule wireMockRule = new WireMockRule(8089)

    def stubAchievementIdsResponse() {
        stubFor(
                get(urlEqualTo("/achievements"))
                        .willReturn(okJson(parseResponseFileContent("/responses/achievements/achievement_ids.json")))
        )
    }

    protected Object parseResponseFile(String file) {
        new JsonSlurper().parse(this.getClass().getResource(file))
    }

    private String parseResponseFileContent(String file) {
        this.getClass().getResource(file).text
    }
}
