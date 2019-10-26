package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.junit.WireMockRule
import com.google.gson.Gson
import groovy.json.JsonSlurper
import org.junit.ClassRule
import spock.lang.Shared
import spock.lang.Specification

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

class WiremockConfig extends Specification {

    @Shared
    @ClassRule
    WireMockRule wireMockRule = new WireMockRule(options()
            .port(8089)
            .notifier(new ConsoleNotifier(true)))

    def gson = new Gson()

    protected Object parseResponse(String file) {
        new JsonSlurper().parse(this.getClass().getResource(file))
    }

    protected String parseResponseText(String file) {
        this.getClass().getResource(file).text
    }
}
