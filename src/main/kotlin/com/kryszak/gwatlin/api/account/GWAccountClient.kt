package com.kryszak.gwatlin.api.account

import com.kryszak.gwatlin.api.account.model.*
import com.kryszak.gwatlin.api.account.model.mastery.AccountMastery
import com.kryszak.gwatlin.api.account.model.mastery.AccountMasteryDetails
import com.kryszak.gwatlin.api.account.model.vault.AccountBankSlot
import com.kryszak.gwatlin.api.account.model.vault.AccountMaterial
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.homeinstance.model.Cat
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

    /**
     * Retrieves information about player's vault
     */
    fun getAccountVault(): List<AccountBankSlot?> {
        return accountClient.getAccountVault()
    }

    /**
     * Retrieves list of daily crafted recipes
     */
    fun getAccountDailyCrafting(): List<String> {
        return accountClient.getDailyCrafting()
    }

    /**
     * Retrieves list of dungeons completed since daily reset
     */
    fun getCompletedDailyDungeons(): List<String> {
        return accountClient.getCompletedDailyDungeons()
    }

    /**
     * Retrieves list of dyes unlocked for account
     */
    fun getUnlockedDyes(): List<Int> {
        return accountClient.getUnlockedDyes()
    }

    /**
     * Retrieves list of unlocked finishers
     */
    fun getFinishers(): List<AccountFinisher> {
        return accountClient.getFinishers()
    }

    /**
     * Retrieves list of unlocked gliders ids
     */
    fun getGliders(): List<Int> {
        return accountClient.getGliders()
    }

    /**
     * Retrieves list of account cats
     */
    fun getCats(): List<Cat> {
        return accountClient.getCats()
    }

    /**
     * Retrieves list of unlocked home instance nodes
     */
    fun getNodes(): List<String> {
        return accountClient.getNodes()
    }

    /**
     * Retrieves shared inventory for account
     */
    fun getInventory(): List<InventoryItem> {
        return accountClient.getInventory()
    }

    /**
     * Retrieves information about account luck
     */
    fun getLuck(): AccountLuck {
        val luck = accountClient.getLuck()
        return when (luck.size) {
            1 -> luck[0]
            0 -> AccountLuck("luck", 0)
            else -> throw ApiRequestException("No luck")
        }
    }

    /**
     * Retrieves ids of unlocked mail carriers
     */
    fun getMailCarriers(): List<Int> {
        return accountClient.getMailCarriers()
    }

    /**
     * Retrieves acquired Hero's Choice Chests since daily-reset
     */
    fun getMapChests(): List<String> {
        return accountClient.getMapChests()
    }

    /**
     * Retrieves account unlocked masteries
     */
    fun getMasteries(): List<AccountMastery> {
        return accountClient.getMasteries()
    }

    /**
     * Retrieves total amount of masteries that are unlocked for an account
     */
    fun getMasteryDetails(): AccountMasteryDetails {
        return accountClient.getMasteryDetails()
    }

    /**
     * Retrieves materials stored in player's vault
     */
    fun getMaterials(): List<AccountMaterial> {
        return accountClient.getMaterials()
    }

    /**
     * Retrieves unlocked minis
     */
    fun getMinis(): List<Int> {
        return accountClient.getMinis()
    }

    /**
     * Retrieves unlocked mount skins
     */
    fun getMountSkins(): List<Int> {
        return accountClient.getMountSkins()
    }

    /**
     * Retrieves unlocked mount types
     */
    fun getMountTypes(): List<String> {
        return accountClient.getMountTypes()
    }

    /**
     * Retrieves novelties that are unlocked for an account
     */
    fun getNovelties(): List<Int> {
        return accountClient.getNovelties()
    }

    /**
     * Retrieves ids of unlocked outfits
     */
    fun getOutfits(): List<Int> {
        return accountClient.getOutfits()
    }

    /**
     * Retrieves ids of unlocked pvp heroes
     */
    fun getPvpHeroes(): List<Int> {
        return accountClient.getPvpHeroes()
    }

    /**
     * Retrieves completed raid encounters since weekly raid reset
     */
    fun getRaids(): List<String> {
        return accountClient.getRaids()
    }

    /**
     * Retrieves ids of unlocked recipes
     */
    fun getRecipes(): List<Int> {
        return accountClient.getRecipes()
    }

    /**
     * Retrieves ids of unlocked skins
     */
    fun getSkins(): List<Int> {
        return accountClient.getSkins()
    }

    /**
     * Retrieves ids of unlocked titles
     */
    fun getTitles(): List<Int> {
        return accountClient.getTitles()
    }

    /**
     * Retrieves contents of wallet in pairs of id of currency and amount
     */
    fun getWallet(): List<CurrencyAmount> {
        return accountClient.getWallet()
    }

    /**
     * Retrieves world bosses that have been killed by the account since daily-reset
     */
    fun getWorldBosses(): List<String> {
        return accountClient.getWorldBosses()
    }
}
