package io.github.kryszak.gwatlin.clients.commerce

import io.github.kryszak.gwatlin.api.commerce.model.delivery.Delivery
import io.github.kryszak.gwatlin.api.commerce.model.transaction.Transaction
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

    fun getHistoricSells(): List<Transaction> {
        return getRequestAuth(historicSellsEndpoint)
    }

    fun getCurrentBuys(): List<Transaction> {
        return getRequestAuth(currentBuysEndpoint)
    }

    fun getCurrentSells(): List<Transaction> {
        return getRequestAuth(currentSellsEndpoint)
    }
}