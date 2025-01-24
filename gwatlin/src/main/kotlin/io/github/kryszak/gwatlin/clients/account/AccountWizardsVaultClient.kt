package io.github.kryszak.gwatlin.clients.account

import io.github.kryszak.gwatlin.api.account.model.wizardsvault.Listing
import io.github.kryszak.gwatlin.api.account.model.wizardsvault.WizardsVaultAchievement
import io.github.kryszak.gwatlin.api.account.model.wizardsvault.WizardsVaultSpecialObjectives
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class AccountWizardsVaultClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val wizardsVaultEndpoint = "/account/wizardsvault"

    fun getListings(): List<Listing> {
        return getRequestAuth("$wizardsVaultEndpoint/listings")
    }

    fun getDailyAchievements(): WizardsVaultAchievement {
        return getRequestAuth("$wizardsVaultEndpoint/daily")
    }

    fun getWeeklyAchievements(): WizardsVaultAchievement {
        return getRequestAuth("$wizardsVaultEndpoint/weekly")
    }

    fun getSpecialObjectives(): WizardsVaultSpecialObjectives {
        return getRequestAuth("$wizardsVaultEndpoint/special")
    }
}