package com.kryszak.gwatlin.api.dailyrewards

import com.kryszak.gwatlin.config.WiremockTestKt
import io.kotest.matchers.shouldBe

internal class DailyRewardsClientTest : WiremockTestKt() {

    private val dailyRewardsClient = GWDailyRewardsClient()

    init {
        should("Get daily crating recipes ids") {
            // given
            stubResponse("/dailycrafting", "/responses/dailyrewards/crafting_recipes_ids.json")

            // when
            val recipesIds = dailyRewardsClient.getDailyCraftingRecipesIds()

            // then
            recipesIds.size shouldBe 5
        }

        should("Get daily map chest ids") {
            // given
            stubResponse("/mapchests", "/responses/dailyrewards/map_chests.json")

            // when
            val chestsIds = dailyRewardsClient.getMapChestsIds()

            // then
            chestsIds.size shouldBe 8
        }

        should("Get daily world bosses ids") {
            // given
            stubResponse("/worldbosses", "/responses/dailyrewards/world_bosses.json")

            // when
            val chestsIds = dailyRewardsClient.getWorldBossesIds()

            // then
            chestsIds.size shouldBe 13
        }
    }
}