package io.github.kryszak.gwatlin.api.commerce

import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class CommerceClientTest : BaseWiremockTest() {

    private val commerceClient = GWCommerceClient()

    init {
        should("Get commerce listing ids") {
            // given
            stubResponse("/v2/commerce/listings", "/responses/commerce/listing/listing_ids.json")

            // when
            val ids = commerceClient.getCommerceListingsIds()

            // then
            ids shouldHaveSize 25384
        }

        should("Get commerce listings") {
            // given
            stubResponse("/v2/commerce/listings?ids=19684,19709", "/responses/commerce/listing/listings.json")

            // when
            val commerceListings = commerceClient.getCommerceListings(listOf(19684, 19709))

            // then
            assertSoftly(commerceListings[0]) {
                id shouldBe 19684
                assertSoftly(buys[0]) {
                    listings shouldBe 170
                    unitPrice shouldBe 81
                    quantity shouldBe 42424
                }
                sells shouldHaveSize 548
            }
        }

        should("Get paged commerce listings") {
            // given
            stubResponse(
                "/v2/commerce/listings?page=0&page_size=10",
                "/responses/commerce/listing/listings_paged.json",
                pageParams = PageParameters(10, 2769, 10, 27668)
            )

            // when
            val pagedListings = commerceClient.getPagedCommerceListings(PageRequest(0, 10))

            // then
            assertSoftly(pagedListings) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 2769
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 27668
            }
        }

        should("Get coin to gem exchange") {
            // given
            stubResponse("/v2/commerce/exchange/coins?quantity=100000", "/responses/commerce/exchange/coin_to_gem.json")

            // when
            val exchange = commerceClient.getCoinToGemExchange(100000)

            // then
            assertSoftly(exchange) {
                coinsPerGem shouldBe 3703
                quantity shouldBe 27
            }
        }

        should("Get gem to coin exchange") {
            // given
            stubResponse("/v2/commerce/exchange/gems?quantity=100", "/responses/commerce/exchange/gem_to_coin.json")

            // when
            val exchange = commerceClient.getGemToCoinExchange(100)

            // then
            assertSoftly(exchange) {
                coinsPerGem shouldBe 970
                quantity shouldBe 97045
            }
        }

        should("Get price ids") {
            // given
            stubResponse("/v2/commerce/prices", "/responses/commerce/price/price_ids.json")

            // when
            val ids = commerceClient.getPriceIds()

            // then
            ids shouldHaveSize 25384
        }

        should("Get prices") {
            // given
            stubResponse("/v2/commerce/prices?ids=19684,19709", "/responses/commerce/price/prices.json")

            // when
            val prices = commerceClient.getPrices(listOf(19684, 19709))

            // then
            assertSoftly(prices[0]) {
                id shouldBe 19684
                whitelisted.shouldBeFalse()
                assertSoftly(buys) {
                    quantity shouldBe 444407
                    unitPrice shouldBe 81
                }
            }
        }

        should("Get paged prices") {
            // given
            stubResponse(
                "/v2/commerce/prices?page=0&page_size=10",
                "/responses/commerce/price/prices_paged.json",
                pageParams = PageParameters(10, 2769, 10, 27668)
            )

            // when
            val pagedPrices = commerceClient.getPagedPrices(PageRequest(0, 10))

            // then
            assertSoftly(pagedPrices) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 2769
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 27668
            }
        }
    }
}