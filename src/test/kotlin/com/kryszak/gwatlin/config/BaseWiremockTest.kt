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

    private val wiremockServer = WireMockServer(
        WireMockConfiguration.options()
            .port(8089)
            .notifier(ConsoleNotifier(!System.getenv("CI").toBoolean()))
    )

    override fun listeners(): List<TestListener> =
        listOf(listener(WireMockListener(wiremockServer, ListenerMode.PER_SPEC)))

    protected fun stubResponse(requestUrl: String, responseFile: String, language: ApiLanguage? = null) {
        wiremockServer.get {
            url equalTo requestUrl
            language?.let { headers contains languageHeader equalTo it.apiString }
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }
    }

    protected fun stubResponse(requestUrl: String, responseFile: String) {
        wiremockServer.get {
            url equalTo requestUrl
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }

    }

    protected fun stubAuthResponse(requestUrl: String, responseFile: String, apiKey: String) {
        wiremockServer.get {
            url equalTo requestUrl
            headers contains "Authorization" equalTo "Bearer $apiKey"
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }
    }

    protected fun stubUnauthenticatedResponse(requestUrl: String, responseFile: String, apiKey: String) {
        wiremockServer.get {
            url equalTo requestUrl
            headers contains "Authorization" equalTo "Bearer $apiKey"
        } returnsJson {
            statusCode = 401
            body = parseResponseText(responseFile)
        }
    }

    protected fun stubAuthResponseWithSchema(
        requestUrl: String,
        responseFile: String,
        apiKey: String,
        schemaVersion: String
    ) {
        wiremockServer.get {
            url equalTo requestUrl
            headers contains "Authorization" equalTo "Bearer $apiKey"
            headers contains "X-Schema-Version" equalTo schemaVersion
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }
    }

    protected fun stubNotFoundResponse(requestUrl: String, responseFile: String) {
        wiremockServer.get {
            url equalTo requestUrl
        } returnsJson {
            statusCode = 404
            body = parseResponseText(responseFile)
        }
    }

    protected fun stubResponseWithSchema(requestUrl: String, responseFile: String, schemaVersion: String) {
        wiremockServer.get {
            url equalTo requestUrl
            headers contains "X-Schema-Version" equalTo schemaVersion
        } returnsJson {
            statusCode = 200
            body = parseResponseText(responseFile)
        }
    }

    private fun parseResponseText(file: String): String {
        return BaseWiremockTest::class.java.getResource(file)?.readText()!!
    }
}