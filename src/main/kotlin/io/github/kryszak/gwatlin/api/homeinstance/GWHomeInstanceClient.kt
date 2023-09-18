package io.github.kryszak.gwatlin.api.homeinstance

import io.github.kryszak.gwatlin.api.homeinstance.model.Cat
import io.github.kryszak.gwatlin.clients.homeinstance.HomeInstanceClient

/**
 * Client for home instance endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWHomeInstanceClient {

    private val homeInstanceClient: HomeInstanceClient = HomeInstanceClient()

    /**
     * Retrieves list of all cat ids
     * @return list of cat ids
     */
    fun getCatIds(): List<Int> {
        return homeInstanceClient.getCatIds()
    }

    /**
     * Retrieves specific cat
     * @param id of cat
     * @return Cat
     * @see io.github.kryszak.gwatlin.api.homeinstance.model.Cat
     */
    fun getCat(id: Int): Cat {
        return homeInstanceClient.getCat(id)
    }

    /**
     * Retrieves list of cats
     * @param ids of cats
     * @return List of cats
     * @see io.github.kryszak.gwatlin.api.homeinstance.model.Cat
     */
    fun getCats(ids: List<Int>): List<Cat> {
        return homeInstanceClient.getCats(ids)
    }

    /**
     * Retrieves list of all available home node instances
     * @return List of nodes
     */
    fun getNodeIds(): List<String> {
        return homeInstanceClient.getNodesIds()
    }
}