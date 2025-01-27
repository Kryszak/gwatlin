package io.github.kryszak.e2e.homestead

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.homestead.GWHomesteadClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class HomesteadE2ETests : BaseE2ESpec() {
    private val client = GWHomesteadClient()

    init {
        context("Homestead") {
            context("Decorations") {
                expect("Fetch decorations") {
                    val decorationIds = client.getDecorationIds().randomElements(100)
                    shouldNotThrowAny { client.getDecorations(decorationIds) }
                }
                context("Categories") {
                    expect("Fetch categories") {
                        val categoryIds = client.getDecorationCategoryIds()
                        shouldNotThrowAny { client.getDecorationCategories(categoryIds) }
                    }
                }
            }
            context("Glyphs") {
                expect("Fetch glyphs") {
                    val glyphIds = client.getGlyphIds()
                    shouldNotThrowAny { client.getGlyphs(glyphIds) }
                }
            }
        }
    }
}