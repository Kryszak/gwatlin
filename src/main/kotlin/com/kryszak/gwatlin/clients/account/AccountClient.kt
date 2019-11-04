package com.kryszak.gwatlin.clients.account

import com.kryszak.gwatlin.api.account.model.Account
import com.kryszak.gwatlin.api.account.model.AccountAchievement
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class AccountClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val accountEndpoint = "account"

    fun getAccountDetails(): Account {
        return getRequestAuth(accountEndpoint)
    }

    fun getAccountAchievements(): List<AccountAchievement> {
        return getRequestAuth("$accountEndpoint/achievements")
    }
}
