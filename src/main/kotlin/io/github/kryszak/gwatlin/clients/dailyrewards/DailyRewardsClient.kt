package io.github.kryszak.gwatlin.clients.dailyrewards

import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class DailyRewardsClient : BaseHttpClient() {

    private val dailyCraftingEndpoint: String = "dailycrafting"

    private val mapChestsEndpoint: String = "mapchests"

    private val worldBossesEndpoint: String = "worldbosses"

    fun getDailyCraftingRecipesIds(): List<String> {
        return getRequest(dailyCraftingEndpoint)
    }

    fun getMapChestsIds(): List<String> {
        return getRequest(mapChestsEndpoint)
    }

    fun getWorldBossesIds(): List<String> {
        return getRequest(worldBossesEndpoint)
    }
}
