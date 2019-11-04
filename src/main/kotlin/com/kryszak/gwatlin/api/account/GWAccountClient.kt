package com.kryszak.gwatlin.api.account

import com.kryszak.gwatlin.api.account.model.Account
import com.kryszak.gwatlin.api.account.model.AccountAchievement
import com.kryszak.gwatlin.clients.account.AccountClient

/**
 * Client for account endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWAccountClient(apiKey: String) {

    private val accountClient: AccountClient = AccountClient(apiKey)

    /**
     * Retrieves information about api account associated with api key
     */
    fun getAccountDetails(): Account {
        return accountClient.getAccountDetails()
    }

    /**
     * Retrieves information about account achievements
     */
    fun getAccountAchievements(): List<AccountAchievement> {
        return accountClient.getAccountAchievements()
    }
}
