package io.github.kryszak.gwatlin.clients.account

import io.github.kryszak.gwatlin.api.account.model.*
import io.github.kryszak.gwatlin.api.account.model.mastery.AccountMastery
import io.github.kryszak.gwatlin.api.account.model.mastery.AccountMasteryDetails
import io.github.kryszak.gwatlin.api.account.model.vault.AccountBankSlot
import io.github.kryszak.gwatlin.api.account.model.vault.AccountMaterial
import io.github.kryszak.gwatlin.api.homeinstance.model.Cat
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class AccountClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val accountEndpoint = "account"

    fun getAccountDetails(): Account {
        return getRequestAuth(accountEndpoint)
    }

    fun getAccountAchievements(): List<AccountAchievement> {
        return getRequestAuth("$accountEndpoint/achievements")
    }

    fun getAccountVault(): List<AccountBankSlot?> {
        return getRequestAuth("$accountEndpoint/bank")
    }

    fun getDailyCrafting(): List<String> {
        return getRequestAuth("$accountEndpoint/dailycrafting")
    }

    fun getCompletedDailyDungeons(): List<String> {
        return getRequestAuth("$accountEndpoint/dungeons")
    }

    fun getUnlockedDyes(): List<Int> {
        return getRequestAuth("$accountEndpoint/dyes")
    }

    fun getFinishers(): List<AccountFinisher> {
        return getRequestAuth("$accountEndpoint/finishers")
    }

    fun getGliders(): List<Int> {
        return getRequestAuth("$accountEndpoint/gliders")
    }

    fun getCats(): List<Cat> {
        return getRequestAuth("$accountEndpoint/home/cats")
    }

    fun getNodes(): List<String> {
        return getRequestAuth("$accountEndpoint/home/nodes")
    }

    fun getInventory(): List<InventoryItem> {
        return getRequestAuth("$accountEndpoint/inventory")
    }

    fun getLuck(): List<AccountLuck> {
        return getRequestAuth("$accountEndpoint/luck")
    }

    fun getMailCarriers(): List<Int> {
        return getRequestAuth("$accountEndpoint/mailcarriers")
    }

    fun getMapChests(): List<String> {
        return getRequestAuth("$accountEndpoint/mapchests")
    }

    fun getMasteries(): List<AccountMastery> {
        return getRequestAuth("$accountEndpoint/masteries")
    }

    fun getMasteryDetails(): AccountMasteryDetails {
        return getRequestAuth("$accountEndpoint/mastery/points")
    }

    fun getMaterials(): List<AccountMaterial> {
        return getRequestAuth("$accountEndpoint/materials")
    }

    fun getMinis(): List<Int> {
        return getRequestAuth("$accountEndpoint/minis")
    }

    fun getMountSkins(): List<Int> {
        return getRequestAuth("$accountEndpoint/mounts/skins")
    }

    fun getMountTypes(): List<String> {
        return getRequestAuth("$accountEndpoint/mounts/types")
    }

    fun getNovelties(): List<Int> {
        return getRequestAuth("$accountEndpoint/novelties")
    }

    fun getOutfits(): List<Int> {
        return getRequestAuth("$accountEndpoint/outfits")
    }

    fun getPvpHeroes(): List<Int> {
        return getRequestAuth("$accountEndpoint/pvp/heroes")
    }

    fun getRaids(): List<String> {
        return getRequestAuth("$accountEndpoint/raids")
    }

    fun getRecipes(): List<Int> {
        return getRequestAuth("$accountEndpoint/recipes")
    }

    fun getSkins(): List<Int> {
        return getRequestAuth("$accountEndpoint/skins")
    }

    fun getTitles(): List<Int> {
        return getRequestAuth("$accountEndpoint/titles")
    }

    fun getWallet(): List<CurrencyAmount> {
        return getRequestAuth("$accountEndpoint/wallet")
    }

    fun getWorldBosses(): List<String> {
        return getRequestAuth("$accountEndpoint/worldbosses")
    }
}
