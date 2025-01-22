package io.github.kryszak.gwatlin.api.dailyrewards

import io.github.kryszak.gwatlin.clients.dailyrewards.DailyRewardsClient

/**
 * Client for daily rewards endpoints.
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWDailyRewardsClient {

    private val dailyRewardsClient: DailyRewardsClient = DailyRewardsClient()

    /**
     * Retrieves list of daily time-gated recipes ids
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/dailycrafting).
     * @return list of crafting recipes ids
     */
    fun getDailyCraftingRecipesIds(): List<String> {
        return dailyRewardsClient.getDailyCraftingRecipesIds()
    }

    /**
     * Retrieves list of daily map chests ids
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/mapchests).
     * @return list of chests ids
     */
    fun getMapChestsIds(): List<String> {
        return dailyRewardsClient.getMapChestsIds()
    }

    /**
     * Retrieves list of scheduled World bosses ids in Core Tyria that reward boss chests
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/worldbosses).
     */
    fun getWorldBossesIds(): List<String> {
        return dailyRewardsClient.getWorldBossesIds()
    }
}