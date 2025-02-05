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
        return getRequest("$recipesEndpoint?ids=$params", language)
    }

    fun searchRecipesWithInput(itemId: Int): List<Int> {
        return getRequest("$searchEndpoint?input=$itemId")
    }

    fun searchRecipesWithOutput(itemId: Int): List<Int> {
        return getRequest("$searchEndpoint?output=$itemId")
    }

    fun getPagedRecipes(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Recipe>> {
        return getPagedRequest("$recipesEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}", language)
    }
}
