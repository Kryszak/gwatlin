package io.github.kryszak.gwatlin.api.commerce

import io.github.kryszak.gwatlin.api.commerce.model.delivery.Delivery
import io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.commerce.CommerceAuthenticatedClient

/**
 * Client for commerce authenticated endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/commerce)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWCommerceAuthenticatedClient(apiKey: String) {

    private val client = CommerceAuthenticatedClient(apiKey)

    /**
     * Provides access to the current items and coins available for pickup on this account.
     * @return io.github.kryszak.gwatlin.api.commerce.model.delivery.Delivery
     */
    fun getDeliveryItems(): Delivery = client.getAccountDeliveries()

    /**
     * Return list of historic buys for account
     * @return io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
     */
    fun getHistoricBuys(): List<Transaction> = client.getHistoricBuys()

    /**
     * Returns paged historic buys for account
     */
    fun getPagedHistoricBuys(pageRequest: PageRequest): PagedResponse<List<Transaction>> =
        client.getPagedHistoricBuys(pageRequest)

    /**
     * Return list of historic sells for account
     * @return io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
     */
    fun getHistoricSells(): List<Transaction> = client.getHistoricSells()

    /**
     * Return paged historic sells for account
     */
    fun getPagedHistoricSells(pageRequest: PageRequest): PagedResponse<List<Transaction>> =
        client.getPagedHistoricSells(pageRequest)

    /**
     * Return list of current buys for account
     * @return io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
     */
    fun getCurrentBuys(): List<Transaction> = client.getCurrentBuys()

    /**
     * Return paged current buys for account
     */
    fun getPagedCurrentBuys(pageRequest: PageRequest): PagedResponse<List<Transaction>> =
        client.getPagedCurrentBuys(pageRequest)

    /**
     * Return list of current sells for account
     * @return io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
     */
    fun getCurrentSells(): List<Transaction> = client.getCurrentSells()

    /**
     * Return paged current sells for account
     */
    fun getPagedCurrentSells(pageRequest: PageRequest): PagedResponse<List<Transaction>> =
        client.getPagedCurrentSells(pageRequest)
}