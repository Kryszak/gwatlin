package io.github.kryszak.gwatlin.api.account

import io.github.kryszak.gwatlin.api.account.model.*
import io.github.kryszak.gwatlin.api.account.model.mastery.AccountMastery
import io.github.kryszak.gwatlin.api.account.model.mastery.AccountMasteryDetails
import io.github.kryszak.gwatlin.api.account.model.vault.AccountBankSlot
import io.github.kryszak.gwatlin.api.account.model.vault.AccountMaterial
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.homeinstance.model.Cat
import io.github.kryszak.gwatlin.clients.account.AccountClient

/**
 * Client for account endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWAccountClient(apiKey: String) {

    private val accountClient: AccountClient = AccountClient(apiKey)

    /**
     * Retrieves information about api account associated with api key
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account).
     */
    fun getAccountDetails(): Account {
        return accountClient.getAccountDetails()
    }

    /**
     * Retrieves information about account achievements
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/achievements).
     */
    fun getAccountAchievements(): List<AccountAchievement> {
        return accountClient.getAccountAchievements()
    }

    /**
     * Retrieves information about player's vault
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/bank).
     */
    fun getAccountVault(): List<AccountBankSlot?> {
        return accountClient.getAccountVault()
    }

    /**
     * Retrieves list of daily crafted recipes
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dailycrafting).
     */
    fun getAccountDailyCrafting(): List<String> {
        return accountClient.getDailyCrafting()
    }

    /**
     * Retrieves list of dungeons completed since daily reset
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dungeons).
     */
    fun getCompletedDailyDungeons(): List<String> {
        return accountClient.getCompletedDailyDungeons()
    }

    /**
     * Retrieves list of dyes unlocked for account
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/dyes).
     */
    fun getUnlockedDyes(): List<Int> {
        return accountClient.getUnlockedDyes()
    }

    /**
     * Retrieves list of unlocked finishers
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/finishers).
     */
    fun getFinishers(): List<AccountFinisher> {
        return accountClient.getFinishers()
    }

    /**
     * Retrieves list of unlocked gliders ids
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/gliders).
     */
    fun getGliders(): List<Int> {
        return accountClient.getGliders()
    }

    /**
     * Retrieves list of account cats
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/home/cats).
     */
    fun getCats(): List<Cat> {
        return accountClient.getCats()
    }

    /**
     * Retrieves list of unlocked home instance nodes
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/home/nodes).
     */
    fun getNodes(): List<String> {
        return accountClient.getNodes()
    }

    /**
     * Retrieves shared inventory for account
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/inventory).
     */
    fun getInventory(): List<InventoryItem?> {
        return accountClient.getInventory()
    }

    /**
     * Retrieves information about account luck
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/luck).
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
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/mailcarriers).
     */
    fun getMailCarriers(): List<Int> {
        return accountClient.getMailCarriers()
    }

    /**
     * Retrieves acquired Hero's Choice Chests since daily-reset
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/mapchests).
     */
    fun getMapChests(): List<String> {
        return accountClient.getMapChests()
    }

    /**
     * Retrieves account unlocked masteries
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/masteries).
     */
    fun getMasteries(): List<AccountMastery> {
        return accountClient.getMasteries()
    }

    /**
     * Retrieves total amount of masteries that are unlocked for an account
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/mastery/points).
     */
    fun getMasteryDetails(): AccountMasteryDetails {
        return accountClient.getMasteryDetails()
    }

    /**
     * Retrieves materials stored in player's vault
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/materials).
     */
    fun getMaterials(): List<AccountMaterial> {
        return accountClient.getMaterials()
    }

    /**
     * Retrieves unlocked minis
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/minis).
     */
    fun getMinis(): List<Int> {
        return accountClient.getMinis()
    }

    /**
     * Retrieves unlocked mount skins
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/mounts/skins).
     */
    fun getMountSkins(): List<Int> {
        return accountClient.getMountSkins()
    }

    /**
     * Retrieves unlocked mount types
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/mount/types).
     */
    fun getMountTypes(): List<String> {
        return accountClient.getMountTypes()
    }

    /**
     * Retrieves novelties that are unlocked for an account
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/novelties).
     */
    fun getNovelties(): List<Int> {
        return accountClient.getNovelties()
    }

    /**
     * Retrieves ids of unlocked outfits
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/outfits).
     */
    fun getOutfits(): List<Int> {
        return accountClient.getOutfits()
    }

    /**
     * Retrieves ids of unlocked pvp heroes
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/pvp/heroes).
     */
    fun getPvpHeroes(): List<Int> {
        return accountClient.getPvpHeroes()
    }

    /**
     * Retrieves completed raid encounters since weekly raid reset
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/raids).
     */
    fun getRaids(): List<String> {
        return accountClient.getRaids()
    }

    /**
     * Retrieves ids of unlocked recipes
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/recipes).
     */
    fun getRecipes(): List<Int> {
        return accountClient.getRecipes()
    }

    /**
     * Retrieves ids of unlocked skins
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/skins).
     */
    fun getSkins(): List<Int> {
        return accountClient.getSkins()
    }

    /**
     * Retrieves ids of unlocked titles
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/titles).
     */
    fun getTitles(): List<Int> {
        return accountClient.getTitles()
    }

    /**
     * Retrieves contents of wallet in pairs of id of currency and amount
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/wallet).
     */
    fun getWallet(): List<CurrencyAmount> {
        return accountClient.getWallet()
    }

    /**
     * Retrieves world bosses that have been killed by the account since daily-reset
     *
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/account/worldbosses).
     */
    fun getWorldBosses(): List<String> {
        return accountClient.getWorldBosses()
    }
}
