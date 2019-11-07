package com.kryszak.gwatlin.api.commerce

import com.kryszak.gwatlin.api.commerce.model.exchange.CoinToGemExchange
import com.kryszak.gwatlin.api.commerce.model.listing.CommerceListing
import com.kryszak.gwatlin.api.commerce.model.price.Price
import com.kryszak.gwatlin.clients.commerce.CommerceClient

/**
 * Client for commerce endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWCommerceClient {

    private val commerceClient: CommerceClient = CommerceClient()

    /**
     * Retrieves list of all listing ids
     */
    fun getCommerceListingsIds(): List<Int> {
        return commerceClient.getCommerceListingsIds()
    }

    /**
     * Returns current buy and sell listings from the trading post
     */
    fun getCommerceListings(ids: List<Int>): List<CommerceListing> {
        val params = ids.joinToString(",")
        return commerceClient.getCommerceListings(ids)
    }

    /**
     * Returns the current coins to gems exchange rate
     */
    fun getCoinToGemExchange(quantity: Int): CoinToGemExchange {
        return commerceClient.getCoinsToGemsExchange(quantity)
    }

    /**
     * Returns the current gem to coins exchange rate
     */
    fun getGemToCoinExchange(quantity: Int): CoinToGemExchange {
        return commerceClient.getGemsToCoinsExchange(quantity)
    }

    /**
     * Retrieves list of all prices
     */
    fun getPriceIds(): List<Int> {
        return commerceClient.getPriceIds()
    }

    /**
     * Returns current aggregated buy and sell listing information from the trading post.
     */
    fun getPrices(ids: List<Int>): List<Price> {
        return commerceClient.getPrices(ids)
    }
}