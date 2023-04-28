package com.kryszak.gwatlin.api.commerce

import com.kryszak.gwatlin.config.WiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.shouldBe

internal class CommerceClientTest : WiremockTest() {

    private val commerceClient = GWCommerceClient()

    init {
        should("Get commerce listing ids") {
            // given
            stubResponse("/commerce/listings", "/responses/commerce/listing_ids.json")

            // when
            val ids = commerceClient.getCommerceListingsIds()

            // then
            ids.size shouldBe 25384
        }

        should("Get commerce listings") {
            // given
            stubResponse("/commerce/listings?ids=19684,19709", "/responses/commerce/listings.json")

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
                sells.size shouldBe 548
            }
        }

        should("Get coin to gem exchange") {
            // given
            stubResponse("/commerce/exchange/coins?quantity=100000", "/responses/commerce/coin_to_gem.json")

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
            stubResponse("/commerce/exchange/gems?quantity=100", "/responses/commerce/gem_to_coin.json")

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
            stubResponse("/commerce/prices", "/responses/commerce/price_ids.json")

            // when
            val ids = commerceClient.getPriceIds()

            // then
            ids.size shouldBe 25384
        }

        should("Get prices") {
            // given
            stubResponse("/commerce/prices?ids=19684,19709", "/responses/commerce/prices.json")

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
    }
}