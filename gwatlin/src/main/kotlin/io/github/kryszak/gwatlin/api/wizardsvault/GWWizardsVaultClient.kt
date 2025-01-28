package io.github.kryszak.gwatlin.api.wizardsvault

import io.github.kryszak.gwatlin.api.wizardsvault.model.Listing
import io.github.kryszak.gwatlin.api.wizardsvault.model.Objective
import io.github.kryszak.gwatlin.api.wizardsvault.model.WizardsVaultSeason
import io.github.kryszak.gwatlin.clients.wizardsvault.WizardsVaultClient

/**
 * Client for wvw endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/wizardsvault)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWWizardsVaultClient {

    private val wizardsVaultClient = WizardsVaultClient()

    /**
     * Returns current wizard's vault season
     */
    fun getSeason(): WizardsVaultSeason {
        return wizardsVaultClient.getSeason()
    }

    /**
     * Returns list of wizard's vault listing ids
     */
    fun getListingIds(): List<Int> {
        return wizardsVaultClient.getListingIds()
    }

    /**
     * Returns listing for given
     * @param id of listing
     */
    fun getListing(id: Int): Listing {
        return wizardsVaultClient.getListing(id)
    }

    /**
     * Returns listings for given
     * @param ids of listings
     */
    fun getListings(ids: List<Int>): List<Listing> {
        return wizardsVaultClient.getListings(ids)
    }

    /**
     * Returns list of wizard's vault objective ids
     */
    fun getObjectiveIds(): List<Int> {
        return wizardsVaultClient.getObjectiveIds()
    }

    /**
     * Returns objective for given
     * @param id of objective
     */
    fun getObjective(id: Int): Objective {
        return wizardsVaultClient.getObjective(id)
    }

    /**
     * Returns objectives for given
     * @param ids of objectives
     */
    fun getObjectives(ids: List<Int>): List<Objective> {
        return wizardsVaultClient.getObjectives(ids)
    }
}