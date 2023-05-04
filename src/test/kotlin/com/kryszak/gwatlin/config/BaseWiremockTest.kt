package com.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.kryszak.gwatlin.api.ApiLanguage
import com.marcinziolo.kotlin.wiremock.contains
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener

internal open class BaseWiremockTest : ShouldSpec() {
    private val languageHeader = "Accept-Language"
    private val authorizationHeader = "Authorization"
    private val schemaVersionHeader = "X-Schema-Version"

    private val wiremockServer = WireMockServer(
        WireMockConfiguration.options()
            .port(8089)
            .notifier(ConsoleNotifier(!System.getenv("CI").toBoolean()))
    )

    override fun listeners(): List<TestListener> =
        listOf(listener(WireMockListener(wiremockServer, ListenerMode.PER_SPEC)))

    protected fun stubResponse(
        requestUrl: String,
        responseFile: String,
        language: ApiLanguage? = null,
        apiKey: String? = null,
        schemaVersion: String? = null,
        responseStatus: Int = 200
    ) {
        wiremockServer.get {
            url equalTo requestUrl
            language?.let { headers contains languageHeader equalTo it.apiString }
            apiKey?.let { headers contains authorizationHeader equalTo "Bearer $it" }
            schemaVersion?.let { headers contains schemaVersionHeader equalTo it }
        } returnsJson {
            statusCode = responseStatus
            body = parseResponseText(responseFile)
        }
    }

    private fun parseResponseText(file: String): String {
        return BaseWiremockTest::class.java.getResource(file)?.readText()!!
    }
}