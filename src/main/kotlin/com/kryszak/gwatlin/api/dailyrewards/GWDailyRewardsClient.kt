package com.kryszak.gwatlin.api.dailyrewards

import com.kryszak.gwatlin.clients.dailyrewards.DailyRewardsClient

/**
 * Client for daily rewards endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWDailyRewardsClient {

    private val dailyRewardsClient: DailyRewardsClient = DailyRewardsClient()

    /**
     * Retrieves list of daily time-gated recipes ids
     * @return list of crafting recipes ids
     */
    fun getDailyCraftingRecipesIds(): List<String> {
        return dailyRewardsClient.getDailyCraftingRecipesIds()
    }

    /**
     * Retrieves list of daily map chests ids
     * @return list of chests ids
     */
    fun getMapChestsIds(): List<String> {
        return dailyRewardsClient.getMapChestsIds()
    }

    /**
     * Retrieves list of scheduled World bosses ids in Core Tyria that reward boss chests
     */
    fun getWorldBossesIds(): List<String> {
        return dailyRewardsClient.getWorldBossesIds()
    }
}