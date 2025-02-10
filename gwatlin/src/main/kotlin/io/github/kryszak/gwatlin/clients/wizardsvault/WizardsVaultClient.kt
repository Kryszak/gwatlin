package io.github.kryszak.gwatlin.clients.wizardsvault

import io.github.kryszak.gwatlin.api.wizardsvault.model.Listing
import io.github.kryszak.gwatlin.api.wizardsvault.model.Objective
import io.github.kryszak.gwatlin.api.wizardsvault.model.WizardsVaultSeason
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class WizardsVaultClient : BaseHttpClient() {

    private val endpoint = "/wizardsvault"

    private val listingsEndpoint = "$endpoint/listings"

    private val objectivesEndpoint = "$endpoint/objectives"

    fun getSeason(): WizardsVaultSeason {
        return getRequest(endpoint)
    }

    fun getListingIds(): List<Int> {
        return getRequest(listingsEndpoint)
    }

    fun getListing(id: Int): Listing {
        return getRequest("$listingsEndpoint/$id")
    }

    fun getListings(ids: List<Int>): List<Listing> {
        val params = ids.joinToString(",")
        return getRequest(listingsEndpoint, listOf("ids" to params))
    }

    fun getObjectiveIds(): List<Int> {
        return getRequest(objectivesEndpoint)
    }

    fun getObjective(id: Int): Objective {
        return getRequest("$objectivesEndpoint/$id")
    }

    fun getObjectives(ids: List<Int>): List<Objective> {
        val params = ids.joinToString(",")
        return getRequest(objectivesEndpoint, listOf("ids" to params))
    }
}