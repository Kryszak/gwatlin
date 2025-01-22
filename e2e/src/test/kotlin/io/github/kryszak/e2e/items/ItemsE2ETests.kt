package io.github.kryszak.e2e.items

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.items.*
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class ItemsE2ETests : BaseE2ESpec() {
    init {
        context("Items") {
            val client = GWItemsClient()
            expect("Fetch random items") {
                val itemIds = client.getItemIds().randomElements(100)
                shouldNotThrowAny { client.getItems(itemIds) }
            }
        }
        context("Item stats") {
            val client = GWItemStatsClient()
            expect("Fetch item stats") {
                val itemStatIds = client.getItemStatsIds().randomElements(100)
                shouldNotThrowAny { client.getItemStats(itemStatIds) }
            }
        }
        context("Materials") {
            val client = GWMaterialsClient()
            expect("Fetch materials") {
                val materialIds = client.getMaterialIds()
                shouldNotThrowAny { client.getMaterials(materialIds) }
            }
        }
        context("Recipes") {
            val client = GWRecipesClient()
            expect("Fetch recipes") {
                val recipeIds = client.getRecipeIds().randomElements(100)
                shouldNotThrowAny { client.getRecipes(recipeIds) }
            }
        }
    }
}