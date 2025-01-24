package io.github.kryszak.gwatlin.api.account

import io.github.kryszak.gwatlin.api.account.model.wizardsvault.Listing
import io.github.kryszak.gwatlin.api.account.model.wizardsvault.WizardsVaultAchievement
import io.github.kryszak.gwatlin.api.account.model.wizardsvault.WizardsVaultSpecialObjectives
import io.github.kryszak.gwatlin.clients.account.AccountWizardsVaultClient

/**
 * Client for account wizard's vault endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/wizardsvault).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWAccountWizardsVaultClient(apiKey: String) {

    private val wizardsVaultClient = AccountWizardsVaultClient(apiKey)

    /**
     * Retrieves the current set of Wizard's Vault rewards, along with details about which have already been purchased by the account, and in what quantity.
     */
    fun getListings(): List<Listing> {
        return wizardsVaultClient.getListings()
    }

    /**
     * Retrieves the current set of daily Wizard's Vault achievements for the account.
     */
    fun getDailyAchievements(): WizardsVaultAchievement {
        return wizardsVaultClient.getDailyAchievements()
    }

    /**
     * Retrieves the current set of weekly Wizard's Vault achievements for the account.
     */
    fun getWeeklyAchievements(): WizardsVaultAchievement {
        return wizardsVaultClient.getWeeklyAchievements()
    }

    /**
     * Retrieves the current set of special Wizard's Vault achievements for the account.
     */
    fun getSpecialObjectives(): WizardsVaultSpecialObjectives {
        return wizardsVaultClient.getSpecialObjectives()
    }
}