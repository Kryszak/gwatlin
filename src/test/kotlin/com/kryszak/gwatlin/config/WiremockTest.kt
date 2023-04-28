package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.google.gson.Gson
import com.kryszak.gwatlin.api.ApiLanguage
import com.marcinziolo.kotlin.wiremock.contains
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener

internal open class WiremockTestKt : ShouldSpec({
}) {
    private val wiremockServer = WireMockServer(
        WireMockConfiguration.options()
            .port(8089)
            .notifier(ConsoleNotifier(!System.getenv("CI").toBoolean()))
    )

    override fun listeners(): List<TestListener> =
        listOf(listener(WireMockListener(wiremockServer, ListenerMode.PER_SPEC)))

    val gson = Gson()

    protected fun stubResponse(requestUrl: String, responseFile: String) {
        wiremockServer.get {
            url equalTo requestUrl
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }

    }

    protected fun stubResponseWithLanguage(requestUrl: String, responseFile: String, language: ApiLanguage) {
        wiremockServer.get {
            url equalTo requestUrl
            headers contains "Accept-Language" equalTo language.apiString
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }
    }

    protected fun parseResponseText(file: String): String {
        return WiremockTestKt::class.java.getResource(file)?.readText()!!
    }
}