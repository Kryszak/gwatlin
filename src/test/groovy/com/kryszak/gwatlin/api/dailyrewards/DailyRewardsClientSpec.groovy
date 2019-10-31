package com.kryszak.gwatlin.api.dailyrewards

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class DailyRewardsClientSpec extends WiremockConfig {

    @Subject
    def dailyRewardsClient = new GWDailyRewardsClient()

    def "Should get daily crating recipes ids"() {
        given: "Expected list of recipes ids"
        def ids = parseResponse("/responses/dailyrewards/crafting_recipes_ids.json")

        and: "External api is stubbed"
        stubResponse("/dailycrafting", "/responses/dailyrewards/crafting_recipes_ids.json")

        when: "Retrieving list of recipes ids"
        def recipesIds = dailyRewardsClient.getDailyCraftingRecipesIds()

        then: "Retrieved list matches expected"
        recipesIds == ids
    }

    def "Should get daily map chest ids"() {
        given: "Expected list of chest ids"
        def ids = parseResponse("/responses/dailyrewards/map_chests.json")

        and: "External api is stubbed"
        stubResponse("/mapchests", "/responses/dailyrewards/map_chests.json")

        when: "Retrieving list of chests ids"
        def chestsIds = dailyRewardsClient.getMapChestsIds()

        then: "Retrieved list matches expected"
        chestsIds == ids
    }

    def "Should get daily world bosses ids"() {
        given: "Expected list of world bosses ids"
        def ids = parseResponse("/responses/dailyrewards/world_bosses.json")

        and: "External api is stubbed"
        stubResponse("/worldbosses", "/responses/dailyrewards/world_bosses.json")

        when: "Retrieving list of world bosses ids"
        def chestsIds = dailyRewardsClient.getWorldBossesIds()

        then: "Retrieved list matches expected"
        chestsIds == ids
    }
}
