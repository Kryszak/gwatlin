package com.kryszak.gwatlin.config


import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import com.kryszak.gwatlin.api.ApiLanguage
import groovy.json.JsonSlurper
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.client.WireMock.*

class WiremockTest extends Specification {

    @Shared
    WireMockRule wireMockRule = WiremockInstance.getInstance().wiremockRule

    def gson = new Gson()

    def setupSpec() {
        wireMockRule.start()
    }

    protected def stubResponse(String url, String responseFile) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubResponseWithLanguage(String url, String responseFile, ApiLanguage language) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Accept-Language", equalTo(language.apiString))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubResponseWithSchemaAndLanguage(String url, String responseFile, ApiLanguage language, String schemaVersion) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Accept-Language", equalTo(language.apiString))
                        .withHeader("X-Schema-Version", equalTo(schemaVersion))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubResponseWithSchema(String url, String responseFile, String schemaVersion) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("X-Schema-Version", equalTo(schemaVersion))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubNotFoundResponse(String url, String responseFile) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .willReturn(notFound().withBody(parseResponseText(responseFile)))
        )
    }

    protected def stubAuthResponse(String url, String responseFile, String auth) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubAuthResponseWithLanguage(String url, String responseFile, ApiLanguage language) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .withHeader("Accept-Language", equalTo(language.apiString))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubAuthResponseWithSchemaAndLanguage(String url, String responseFile, ApiLanguage language, String schemaVersion) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .withHeader("Accept-Language", equalTo(language.apiString))
                        .withHeader("X-Schema-Version", equalTo(schemaVersion))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubAuthResponseWithSchema(String url, String responseFile, String auth, String schemaVersion) {
        wireMockRule.stubFor(
                get(urlEqualTo(url))
                        .withHeader("Authorization", equalTo("Bearer $auth"))
                        .withHeader("X-Schema-Version", equalTo(schemaVersion))
                        .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected def stubUnauthenticatedResponse(String url, String responseFile, String auth) {
        wireMockRule.stubFor(
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