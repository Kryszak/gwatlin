package io.github.kryszak.gwatlin.api.commerce

import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime

internal class CommerceAuthenticatedClientTest : BaseWiremockTest() {

    private val apiKey = "1234"

    private val client = GWCommerceAuthenticatedClient(apiKey)

    init {
        should("Get pending account delivery") {
            // given
            stubResponse("/v2/commerce/delivery", "/responses/commerce/delivery.json", apiKey = apiKey)

            // when
            val delivery = client.getDeliveryItems()

            // then
            assertSoftly(delivery) {
                coins shouldBe 4294967295
                items shouldHaveSize 3
                assertSoftly(items[0]) {
                    id shouldBe 19700
                    count shouldBe 3
                }
            }
        }

        should("Get historic buys") {
            // given
            stubResponse(
                "/v2/commerce/transactions/history/buys",
                "/responses/commerce/authenticated/transactions_historic.json",
                apiKey = apiKey
            )

            // when
            val transactions = client.getHistoricBuys()

            // then
            assertSoftly(transactions) {
                it shouldHaveSize 2
                assertSoftly(transactions[0]) {
                    id shouldBe 2750477618
                    itemId shouldBe 24612
                    price shouldBe 1788
                    quantity shouldBe 1
                    created shouldBe OffsetDateTime.parse("2015-05-09T17:13:26+00:00")
                    purchased shouldBe OffsetDateTime.parse("2015-05-09T17:24:20+00:00")
                }
            }
        }

        should("Get paged historic buys") {
            // given
            stubResponse(
                "/v2/commerce/transactions/history/buys?page=0&page_size=10",
                "/responses/commerce/authenticated/transactions_historic.json",
                apiKey = apiKey,
                pageParams = PageParameters(10, 1, 2, 2)
            )

            // when
            val pagedTransactions = client.getPagedHistoricBuys(PageRequest(0, 10))

            // then
            assertSoftly(pagedTransactions) {
                it.data shouldHaveSize 2
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 1
                it.resultCount shouldBe 2
                it.resultTotal shouldBe 2
            }
        }

        should("Get historic sells") {
            // given
            stubResponse(
                "/v2/commerce/transactions/history/sells",
                "/responses/commerce/authenticated/transactions_historic.json",
                apiKey = apiKey
            )

            // when
            val transactions = client.getHistoricSells()

            // then
            assertSoftly(transactions) {
                it shouldHaveSize 2
                assertSoftly(transactions[0]) {
                    id shouldBe 2750477618
                    itemId shouldBe 24612
                    price shouldBe 1788
                    quantity shouldBe 1
                    created shouldBe OffsetDateTime.parse("2015-05-09T17:13:26+00:00")
                    purchased shouldBe OffsetDateTime.parse("2015-05-09T17:24:20+00:00")
                }
            }
        }

        should("Get paged historic sells") {
            // given
            stubResponse(
                "/v2/commerce/transactions/history/sells?page=0&page_size=10",
                "/responses/commerce/authenticated/transactions_historic.json",
                apiKey = apiKey,
                pageParams = PageParameters(10, 1, 2, 2)
            )

            // when
            val pagedTransactions = client.getPagedHistoricSells(PageRequest(0, 10))

            // then
            assertSoftly(pagedTransactions) {
                it.data shouldHaveSize 2
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 1
                it.resultCount shouldBe 2
                it.resultTotal shouldBe 2
            }
        }

        should("Get current buys") {
            // given
            stubResponse(
                "/v2/commerce/transactions/current/buys",
                "/responses/commerce/authenticated/transactions_current.json",
                apiKey = apiKey
            )

            // when
            val transactions = client.getCurrentBuys()

            // then
            assertSoftly(transactions) {
                it shouldHaveSize 2
                assertSoftly(transactions[0]) {
                    id shouldBe 2750477618
                    itemId shouldBe 24612
                    price shouldBe 1788
                    quantity shouldBe 1
                    created shouldBe OffsetDateTime.parse("2015-05-09T17:13:26+00:00")
                    purchased.shouldBeNull()
                }
            }
        }

        should("Get paged current buys") {
            // given
            stubResponse(
                "/v2/commerce/transactions/current/buys?page=0&page_size=10",
                "/responses/commerce/authenticated/transactions_current.json",
                apiKey = apiKey,
                pageParams = PageParameters(10, 1, 2, 2)
            )

            // when
            val pagedTransactions = client.getPagedCurrentBuys(PageRequest(0, 10))

            // then
            assertSoftly(pagedTransactions) {
                it.data shouldHaveSize 2
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 1
                it.resultCount shouldBe 2
                it.resultTotal shouldBe 2
            }
        }

        should("Get current sells") {
            // given
            stubResponse(
                "/v2/commerce/transactions/current/sells",
                "/responses/commerce/authenticated/transactions_current.json",
                apiKey = apiKey
            )

            // when
            val transactions = client.getCurrentSells()

            // then
            assertSoftly(transactions) {
                it shouldHaveSize 2
                assertSoftly(transactions[0]) {
                    id shouldBe 2750477618
                    itemId shouldBe 24612
                    price shouldBe 1788
                    quantity shouldBe 1
                    created shouldBe OffsetDateTime.parse("2015-05-09T17:13:26+00:00")
                    purchased.shouldBeNull()
                }
            }
        }

        should("Get paged current sells") {
            // given
            stubResponse(
                "/v2/commerce/transactions/current/sells?page=0&page_size=10",
                "/responses/commerce/authenticated/transactions_current.json",
                apiKey = apiKey,
                pageParams = PageParameters(10, 1, 2, 2)
            )

            // when
            val pagedTransactions = client.getPagedCurrentSells(PageRequest(0, 10))

            // then
            assertSoftly(pagedTransactions) {
                it.data shouldHaveSize 2
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 1
                it.resultCount shouldBe 2
                it.resultTotal shouldBe 2
            }
        }
    }
}
