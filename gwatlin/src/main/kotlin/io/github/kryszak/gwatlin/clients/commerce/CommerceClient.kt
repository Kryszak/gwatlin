package io.github.kryszak.gwatlin.clients.commerce

import io.github.kryszak.gwatlin.api.commerce.model.exchange.CoinToGemExchange
import io.github.kryszak.gwatlin.api.commerce.model.listing.CommerceListing
import io.github.kryszak.gwatlin.api.commerce.model.price.Price
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class CommerceClient : BaseHttpClient() {

    private val commerceEndpoint = "/commerce"

    fun getCommerceListingsIds(): List<Int> {
        return getRequest("$commerceEndpoint/listings")
    }

    fun getCommerceListings(ids: List<Int>): List<CommerceListing> {
        val params = ids.joinToString(",")
        return getRequest("$commerceEndpoint/listings?ids=$params")
    }

    fun getPagedCommerceListings(pageRequest: PageRequest): PagedResponse<List<CommerceListing>> {
        return getPagedRequest("$commerceEndpoint/listings?page=${pageRequest.page}&page_size=${pageRequest.size}")
    }

    fun getCoinsToGemsExchange(quantity: Int): CoinToGemExchange {
        return getRequest("$commerceEndpoint/exchange/coins?quantity=$quantity")
    }

    fun getGemsToCoinsExchange(quantity: Int): CoinToGemExchange {
        return getRequest("$commerceEndpoint/exchange/gems?quantity=$quantity")
    }

    fun getPriceIds(): List<Int> {
        return getRequest("$commerceEndpoint/prices")
    }

    fun getPrices(ids: List<Int>): List<Price> {
        val params = ids.joinToString(",")
        return getRequest("$commerceEndpoint/prices?ids=$params")
    }

    fun getPagedPrices(pageRequest: PageRequest): PagedResponse<List<Price>> {
        return getPagedRequest("$commerceEndpoint/prices?page=${pageRequest.page}&page_size=${pageRequest.size}")
    }
}