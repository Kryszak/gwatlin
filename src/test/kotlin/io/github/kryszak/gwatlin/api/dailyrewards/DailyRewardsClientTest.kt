package io.github.kryszak.gwatlin.api.dailyrewards

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.matchers.collections.shouldHaveSize

internal class DailyRewardsClientTest : BaseWiremockTest() {

    private val dailyRewardsClient = GWDailyRewardsClient()

    init {
        should("Get daily crating recipes ids") {
            // given
            stubResponse("/dailycrafting", "/responses/dailyrewards/crafting_recipes_ids.json")

            // when
            val recipesIds = dailyRewardsClient.getDailyCraftingRecipesIds()

            // then
            recipesIds shouldHaveSize 5
        }

        should("Get daily map chest ids") {
            // given
            stubResponse("/mapchests", "/responses/dailyrewards/map_chests.json")

            // when
            val chestsIds = dailyRewardsClient.getMapChestsIds()

            // then
            chestsIds shouldHaveSize 8
        }

        should("Get daily world bosses ids") {
            // given
            stubResponse("/worldbosses", "/responses/dailyrewards/world_bosses.json")

            // when
            val chestsIds = dailyRewardsClient.getWorldBossesIds()

            // then
            chestsIds shouldHaveSize 13
        }
    }
}