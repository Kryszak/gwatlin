package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.recipe.Recipe
import com.kryszak.gwatlin.clients.items.RecipesClient

/**
 * Client for recipes endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWRecipesClient {

    private val recipesClient: RecipesClient = RecipesClient()

    /**
     * Retrieves all recipe ids
     */
    fun getRecipeIds(): List<Int> {
        return recipesClient.getRecipeIds()
    }

    /**
     * Retrieves specific recipes
     * @param ids of recipes
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.recipe.Recipe
     */
    @JvmOverloads
    fun getRecipes(ids: List<Int>, language: ApiLanguage? = null): List<Recipe> {
        return recipesClient.getRecipes(ids, language)
    }

    /**
     * Searches for recipe ids with one of ingredients being given item
     * @param itemId of ingredient
     */
    fun searchRecipesWithInput(itemId: Int): List<Int> {
        return recipesClient.searchRecipesWithInput(itemId)
    }

    /**
     * Searches for recipe ids with product being given item
     * @param itemId of product
     */
    fun searchRecipesWithOutput(itemId: Int): List<Int> {
        return recipesClient.searchRecipesWithOutput(itemId)
    }
}