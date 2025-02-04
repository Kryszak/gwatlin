package io.github.kryszak.gwatlin.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.absent
import com.github.tomakehurst.wiremock.client.WireMock.matching
import com.github.tomakehurst.wiremock.common.ConsoleNotifier
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.marcinziolo.kotlin.wiremock.equalTo
import com.marcinziolo.kotlin.wiremock.get
import com.marcinziolo.kotlin.wiremock.returnsJson
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.util.ResourcesUtil
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.extensions.wiremock.ListenerMode
import io.kotest.extensions.wiremock.WireMockListener

internal open class BaseWiremockTest : ShouldSpec() {
    private val languageHeader = "Accept-Language"
    private val authorizationHeader = "Authorization"
    private val schemaVersionHeader = "X-Schema-Version"
    private val pageSizeHeader = "X-Page-Size"
    private val pageTotalHeader = "X-Page-Total"
    private val resultCountHeader = "X-Result-Count"
    private val resultTotalHeader = "X-Result-Total"

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
        responseStatus: Int = 200,
        pageParams: PageParameters? = null,
    ) {
        wiremockServer.get {
            url equalTo requestUrl
            withBuilder {
                val languageHeaderValue = language?.let { matching(it.apiString) } ?: absent()
                withHeader(languageHeader, languageHeaderValue)
            }
            withBuilder {
                val authorizationHeaderValue = apiKey?.let { matching("Bearer $it") } ?: absent()
                withHeader(authorizationHeader, authorizationHeaderValue)
            }
            withBuilder {
                val schemaVersionHeaderValue = schemaVersion?.let { matching(it) } ?: absent()
                withHeader(schemaVersionHeader, schemaVersionHeaderValue)
            }
        } returnsJson {
            statusCode = responseStatus
            body = parseResponseText(responseFile)
            withBuilder {
                pageParams?.let {
                    withHeader(pageSizeHeader, it.pageSize.toString())
                    withHeader(pageTotalHeader, it.pageTotal.toString())
                    withHeader(resultCountHeader, it.resultCount.toString())
                    withHeader(resultTotalHeader, it.resultTotal.toString())
                }
            }
        }
    }

    private fun parseResponseText(file: String): String {
        return ResourcesUtil.readResource(file)
    }

    internal data class PageParameters(
        val pageSize: Int,
        val pageTotal: Int,
        val resultCount: Int,
        val resultTotal: Int,
    )
}