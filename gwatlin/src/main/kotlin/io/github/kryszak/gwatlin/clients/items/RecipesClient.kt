package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.recipe.Recipe
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class RecipesClient : BaseHttpClient() {

    private val recipesEndpoint = "/recipes"

    private val searchEndpoint = "$recipesEndpoint/search"

    fun getRecipeIds(): List<Int> {
        return getRequest(recipesEndpoint)
    }

    fun getRecipes(ids: List<Int>, language: ApiLanguage?): List<Recipe> {
        val params = ids.joinToString(",")
        return getRequest(recipesEndpoint, listOf("ids" to params), language)
    }

    fun searchRecipesWithInput(itemId: Int): List<Int> {
        return getRequest(searchEndpoint, listOf("input" to itemId.toString()))
    }

    fun searchRecipesWithOutput(itemId: Int): List<Int> {
        return getRequest(searchEndpoint, listOf("output" to itemId.toString()))
    }

    fun getPagedRecipes(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Recipe>> {
        return getPagedRequest(recipesEndpoint, pageRequest.toQueryParams(), language)
    }
}
