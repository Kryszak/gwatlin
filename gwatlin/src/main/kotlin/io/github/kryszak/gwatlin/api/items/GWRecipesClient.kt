package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.recipe.Recipe
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.items.RecipesClient

/**
 * Client for recipes endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/recipes)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.items.model.recipe.Recipe
     */
    @JvmOverloads
    fun getRecipes(ids: List<Int>, language: ApiLanguage? = null): List<Recipe> {
        return recipesClient.getRecipes(ids, language)
    }

    /**
     * Retrieves paged recipes
     * @param language of returned text (default=en)
     */
    @JvmOverloads
    fun getPagedRecipes(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Recipe>> {
        return recipesClient.getPagedRecipes(pageRequest, language)
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