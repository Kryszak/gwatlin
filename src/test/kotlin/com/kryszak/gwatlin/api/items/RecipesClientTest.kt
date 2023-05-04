package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class RecipesClientTest: BaseWiremockTest() {

    private val recipesClient = GWRecipesClient()
    
    init {
        should("Get recipe ids") {
            // given
            stubResponse("/recipes", "/responses/items/recipe_ids.json")

            // when
            val recipeIds = recipesClient.getRecipeIds()

            // then
            recipeIds shouldHaveSize 12234
        }

        should("Get recipe") {
            // given
            val ids = listOf(1)
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/recipes?ids=1", "/responses/items/recipe.json", lang)

            // when
            val recipes = recipesClient.getRecipes(ids, lang)

            // then
            assertSoftly(recipes[0]) {
                id shouldBe 1
                type shouldBe "Refinement"
                outputItemId shouldBe 19713
                outputItemCount shouldBe 1
                minRating shouldBe 75
                timeToCraftMs shouldBe 2000
                disciplines shouldHaveSize 4
                flags shouldHaveSize 1
                assertSoftly(ingredients[0]) {
                    itemId shouldBe 19726
                    count shouldBe 2
                }
                chatLink shouldBe "[&CQEAAAA=]"
            }
        }

        should("Find recipes with given ingredient") {
            // given
            val ingredientId = 46731

            stubResponse("/recipes/search?input=46731", "/responses/items/search_recipe_input.json")

            // when
            val recipeIds = recipesClient.searchRecipesWithInput(ingredientId)

            // then
            recipeIds shouldHaveSize 16
        }

        should("Find recipes with given product") {
            // given
            val productId = 50065

            stubResponse("/recipes/search?output=50065", "/responses/items/search_recipe_output.json")

            // when
            val recipeIds = recipesClient.searchRecipesWithOutput(productId)

            // then
            recipeIds shouldHaveSize 3
        }
    }
}