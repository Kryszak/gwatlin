package io.github.kryszak.gwatlin.clients.commerce

import io.github.kryszak.gwatlin.api.commerce.model.delivery.Delivery
import io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class CommerceAuthenticatedClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val deliveryEndpoint = "/commerce/delivery"

    private val transactionsEndpoint = "/commerce/transactions"

    private val historicBuysEndpoint = "$transactionsEndpoint/history/buys"

    private val historicSellsEndpoint = "$transactionsEndpoint/history/sells"

    private val currentBuysEndpoint = "$transactionsEndpoint/current/buys"

    private val currentSellsEndpoint = "$transactionsEndpoint/current/sells"

    fun getAccountDeliveries(): Delivery {
        return getRequestAuth(deliveryEndpoint)
    }

    fun getHistoricBuys(): List<Transaction> {
        return getRequestAuth(historicBuysEndpoint)
    }

    fun getPagedHistoricBuys(pageRequest: PageRequest): PagedResponse<List<Transaction>> {
        return getPagedRequestAuth(historicBuysEndpoint, pageRequest.toQueryParams())
    }

    fun getHistoricSells(): List<Transaction> {
        return getRequestAuth(historicSellsEndpoint)
    }

    fun getPagedHistoricSells(pageRequest: PageRequest): PagedResponse<List<Transaction>> {
        return getPagedRequestAuth(historicSellsEndpoint, pageRequest.toQueryParams())
    }

    fun getCurrentBuys(): List<Transaction> {
        return getRequestAuth(currentBuysEndpoint)
    }

    fun getPagedCurrentBuys(pageRequest: PageRequest): PagedResponse<List<Transaction>> {
        return getPagedRequestAuth(currentBuysEndpoint, pageRequest.toQueryParams())
    }

    fun getCurrentSells(): List<Transaction> {
        return getRequestAuth(currentSellsEndpoint)
    }

    fun getPagedCurrentSells(pageRequest: PageRequest): PagedResponse<List<Transaction>> {
        return getPagedRequestAuth(currentSellsEndpoint, pageRequest.toQueryParams())
    }
}