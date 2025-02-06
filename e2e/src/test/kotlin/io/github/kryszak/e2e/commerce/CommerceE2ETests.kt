package io.github.kryszak.e2e.commerce

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.commerce.GWCommerceClient
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class CommerceE2ETests : BaseE2ESpec() {
    private val client = GWCommerceClient()

    init {
        context("Commerce") {
            expect("Should fetch random prices") {
                val priceIds = client.getPriceIds().randomElements(100)
                shouldNotThrowAny {
                    client.getPrices(priceIds)
                }
            }
            expect("Should fetch paged prices") {
                shouldNotThrowAny { client.getPagedPrices(PageRequest(0, 10)) }
            }
            expect("Should fetch random listings") {
                val listingIds = client.getCommerceListingsIds().randomElements(100)
                shouldNotThrowAny {
                    client.getCommerceListings(listingIds)
                }
            }
            expect("Should fetch paged listings") {
                shouldNotThrowAny { client.getPagedCommerceListings(PageRequest(0, 10)) }
            }
            expect("Should fetch coin to gem exchange") {
                shouldNotThrowAny {
                    client.getCoinToGemExchange(100000)
                }
            }
            expect("Should fetch gem to coin exchange") {
                shouldNotThrowAny {
                    client.getGemToCoinExchange(1000)
                }
            }
        }
    }
}