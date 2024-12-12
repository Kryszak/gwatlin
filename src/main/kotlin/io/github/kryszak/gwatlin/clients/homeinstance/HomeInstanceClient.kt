package io.github.kryszak.gwatlin.clients.homeinstance

import io.github.kryszak.gwatlin.api.homeinstance.model.Cat
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class HomeInstanceClient : BaseHttpClient() {

    private val baseEndpoint: String = "/home"

    private val catsEndpoint: String = "$baseEndpoint/cats"

    private val nodesEndpoint: String = "$baseEndpoint/nodes"

    fun getCatIds(): List<Int> {
        return getRequest(catsEndpoint)
    }

    fun getCat(id: Int): Cat {
        return getRequest("$catsEndpoint/$id")
    }

    fun getCats(ids: List<Int>): List<Cat> {
        val params = ids.joinToString(",")
        return getRequest("$catsEndpoint?ids=$params")
    }

    fun getNodesIds(): List<String> {
        return getRequest(nodesEndpoint)
    }
}
