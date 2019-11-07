package com.kryszak.gwatlin.api.commerce


import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class CommerceClientSpec extends WiremockConfig {

    @Subject
    def commerceClient = new GWCommerceClient()

    def "Should get commerce listing ids"() {
        given: "External api is stubbed"
        stubResponse("/commerce/listings", "/responses/commerce/listing_ids.json")

        when: "Requesting listing ids"
        def ids = commerceClient.getCommerceListingsIds()

        then: "Retrieved list matches expected"
        ids.size() == 25384
    }

    def "Should get commerce listings"() {
        given: "External api is stubbed"
        stubResponse("/commerce/listings?ids=19684,19709", "/responses/commerce/listings.json")

        when: "Requesting listings"
        def commerceListings = commerceClient.getCommerceListings([19684, 19709])

        then: "Retrieved listings match expected"
        verifyAll(commerceListings.get(0)) {
            id == 19684
            verifyAll(buys.get(0)) {
                listings == 170
                unitPrice == 81
                quantity == 42424
            }
            sells.size() == 548
        }
    }

    def "Should get coin to gem exchange"() {
        given: "External api is stubbed"
        stubResponse("/commerce/exchange/coins?quantity=100000", "/responses/commerce/coin_to_gem.json")

        when: "Requesting coin to gem exchange"
        def exchange = commerceClient.getCoinToGemExchange(100000)

        then: "Retrieved exchange matches expected"
        verifyAll(exchange) {
            coinsPerGem == 3703
            quantity == 27
        }
    }

    def "Should get gem to coin exchange"() {
        given: "External api is stubbed"
        stubResponse("/commerce/exchange/gems?quantity=100", "/responses/commerce/gem_to_coin.json")

        when: "Requesting coin to gem exchange"
        def exchange = commerceClient.getGemToCoinExchange(100)

        then: "Retrieved exchange matches expected"
        verifyAll(exchange) {
            coinsPerGem == 970
            quantity == 97045
        }
    }

    def "Should get price ids"() {
        given: "External api is stubbed"
        stubResponse("/commerce/prices", "/responses/commerce/price_ids.json")

        when: "Requesting price ids"
        def ids = commerceClient.getPriceIds()

        then: "Retrieved list matches expected"
        ids.size() == 25384
    }

    def "Should get prices"() {
        given: "External api is stubbed"
        stubResponse("/commerce/prices?ids=19684,19709", "/responses/commerce/prices.json")

        when: "Requesting prices"
        def prices = commerceClient.getPrices([19684, 19709])

        then: "Retrieved prices match expected"
        verifyAll(prices.get(0)) {
            id == 19684
            !whitelisted
            verifyAll(buys) {
                quantity == 444407
                unitPrice == 81
            }
            sells != null
        }
    }
}
