package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import groovy.json.JsonSlurper
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.notFound
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.unauthorized
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class WiremockConfig extends Specification {

    @Shared
    @ClassRule
    WireMockRule wireMockRule = new WireMockRule(options()
            .port(8089)
            .notifier(new ConsoleNotifier(!Boolean.parseBoolean(System.getenv("CI")))))

    def gson = new Gson()

    protected def stubResponse(String url, String responseFile) {
        stubFor(
                get(urlEqualTo(url))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubNotFoundResponse(String url, String responseFile) {
        stubFor(
                get(urlEqualTo(url))
                        .willReturn(notFound().withBody(parseResponseText(responseFile)))
        )
    }

    protected def stubAuthResponse(String url, String responseFile, String auth) {
        stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubUnauthenticatedResponse(String url, String responseFile, String auth) {
        stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .willReturn(unauthorized().withBody(parseResponseText(responseFile)))
        )
    }

    protected Object parseResponse(String file) {
        new JsonSlurper().parse(this.getClass().getResource(file))
    }

    protected String parseResponseText(String file) {
        this.getClass().getResource(file).text
    }
}
