package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class RecipesClientSpec extends WiremockConfig {

    @Subject
    def recipesClient = new GWRecipesClient()

    def "Should get recipe ids"() {
        given: "Expected recipe ids"
        def ids = parseResponse("/responses/items/recipe_ids.json")

        and: "External api is stubbed"
        stubResponse("/recipes", "/responses/items/recipe_ids.json")

        when: "Requesting recipe ids"
        def recipeIds = recipesClient.getRecipeIds()

        then: "Retrieved list matches expected"
        recipeIds == ids
    }

    def "Should get recipe"() {
        given: "Recipe id"
        def ids = [1]

        and: "External api is stubbed"
        stubResponse("/recipes?ids=1&lang=en", "/responses/items/recipe.json")

        when: "Requesting recipe"
        def recipes = recipesClient.getRecipes(ids, "en")

        then: "Retrieved recipe matches expected"
        verifyAll(recipes.get(0)) {
            id == 1
            type == "Refinement"
            outputItemId == 19713
            outputItemCount == 1
            minRating == 75
            timeToCraftMs == 2000
            disciplines.size() == 4
            flags.size() == 1
            verifyAll(ingredients.get(0)) {
                itemId == 19726
                count == 2
            }
            chatLink == "[&CQEAAAA=]"
        }
    }

    def "Should find recipes with given ingredient"() {
        given: "Expected recipe ids"
        def ids = parseResponse("/responses/items/search_recipe_input.json")

        and: "Ingredient id"
        def ingredientId = 46731

        and: "External api is stubbed"
        stubResponse("/recipes/search?input=46731", "/responses/items/search_recipe_input.json")

        when: "Searching for recipes with input"
        def recipeIds = recipesClient.searchRecipesWithInput(ingredientId)

        then: "Retrieved list matches expected"
        recipeIds == ids
    }

    def "Should find recipes with given product"() {
        given: "Expected recipe ids"
        def ids = parseResponse("/responses/items/search_recipe_output.json")

        and: "Product id"
        def productId = 50065

        and: "External api is stubbed"
        stubResponse("/recipes/search?output=50065", "/responses/items/search_recipe_output.json")

        when: "Searching for recipes with product"
        def recipeIds = recipesClient.searchRecipesWithOutput(productId)

        then: "Retrieved list matches expected"
        recipeIds == ids
    }
}
