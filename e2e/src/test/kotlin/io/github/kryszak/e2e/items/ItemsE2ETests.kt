package io.github.kryszak.e2e.items

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.GWItemStatsClient
import io.github.kryszak.gwatlin.api.items.GWItemsClient
import io.github.kryszak.gwatlin.api.items.GWMaterialsClient
import io.github.kryszak.gwatlin.api.items.GWRecipesClient
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class ItemsE2ETests : BaseE2ESpec() {
    init {
        ApiLanguage.entries.forEach { language ->
            context("$language language") {
                context("Items") {
                    val client = GWItemsClient()
                    expect("Fetch random items") {
                        val itemIds = client.getItemIds().randomElements(100)
                        shouldNotThrowAny { client.getItems(itemIds, language) }
                    }
                    expect("Fetch paged items") {
                        shouldNotThrowAny { client.getPagedItems(PageRequest(0, 10), language) }
                    }
                }
                context("Item stats") {
                    val client = GWItemStatsClient()
                    expect("Fetch item stats") {
                        val itemStatIds = client.getItemStatsIds().randomElements(100)
                        shouldNotThrowAny { client.getItemStats(itemStatIds, language) }
                    }
                    expect("Fetch paged item stats") {
                        shouldNotThrowAny { client.getPagedItemStats(PageRequest(0, 10), language) }
                    }
                }
                context("Materials") {
                    val client = GWMaterialsClient()
                    expect("Fetch materials") {
                        val materialIds = client.getMaterialIds()
                        shouldNotThrowAny { client.getMaterials(materialIds, language) }
                    }
                }
                context("Recipes") {
                    val client = GWRecipesClient()
                    expect("Fetch recipes") {
                        val recipeIds = client.getRecipeIds().randomElements(100)
                        shouldNotThrowAny { client.getRecipes(recipeIds, language) }
                    }
                    expect("Fetch paged recipes") {
                        shouldNotThrowAny { client.getPagedRecipes(PageRequest(0, 10), language) }
                    }
                }
            }
        }
    }
}