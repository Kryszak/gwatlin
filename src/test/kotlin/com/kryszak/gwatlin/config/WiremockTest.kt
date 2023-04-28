package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.google.gson.Gson
import com.kryszak.gwatlin.api.ApiLanguage
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener

open class WiremockTestKt : ShouldSpec({
}) {
    private val wiremockServer = WireMockServer(
        WireMockConfiguration.options()
            .port(8089)
            .notifier(ConsoleNotifier(!System.getenv("CI").toBoolean()))
    )

    override fun listeners(): List<TestListener> =
        listOf(listener(WireMockListener(wiremockServer, ListenerMode.PER_SPEC)))

    val gson = Gson()

    protected fun stubResponse(url: String, responseFile: String) {
        wiremockServer.stubFor(
            get(urlEqualTo(url))
                .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected fun stubResponseWithLanguage(url: String, responseFile: String, language: ApiLanguage) {
        wiremockServer.stubFor(
            get(urlEqualTo(url))
                .withHeader("Accept-Language", equalTo(language.apiString))
                .willReturn(okJson(parseResponseText(responseFile)))
        )
    }

    protected fun parseResponseText(file: String): String {
        return WiremockTestKt::class.java.getResource(file)?.readText()!!
    }
}