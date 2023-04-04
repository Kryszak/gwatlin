package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.junit.WireMockRule

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

@Singleton
class WiremockInstance extends WireMockRule {

    def wiremockRule = new WireMockRule(options()
            .port(8089)
            .notifier(new ConsoleNotifier(!Boolean.parseBoolean(System.getenv("CI")))))
}
