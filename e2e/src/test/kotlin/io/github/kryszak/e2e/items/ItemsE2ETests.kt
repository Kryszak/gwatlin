package io.github.kryszak.e2e.items

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.*
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class ItemsE2ETests : BaseE2ESpec() {
    init {
        context("Items") {
            val client = GWItemsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch random items in $language language") {
                    val itemIds = client.getItemIds().randomElements(100)
                    shouldNotThrowAny { client.getItems(itemIds, language) }
                }
            }
        }
        context("Item stats") {
            val client = GWItemStatsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch item stats in $language language") {
                    val itemStatIds = client.getItemStatsIds().randomElements(100)
                    shouldNotThrowAny { client.getItemStats(itemStatIds, language) }
                }
            }
        }
        context("Materials") {
            val client = GWMaterialsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch materials in $language language") {
                    val materialIds = client.getMaterialIds()
                    shouldNotThrowAny { client.getMaterials(materialIds, language) }
                }
            }
        }
        context("Recipes") {
            val client = GWRecipesClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch recipes in $language language") {
                    val recipeIds = client.getRecipeIds().randomElements(100)
                    shouldNotThrowAny { client.getRecipes(recipeIds, language) }
                }
            }
        }
    }
}