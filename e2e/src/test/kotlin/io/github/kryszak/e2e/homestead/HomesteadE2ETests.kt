package io.github.kryszak.e2e.homestead

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.homestead.GWHomesteadClient
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class HomesteadE2ETests : BaseE2ESpec() {
    private val client = GWHomesteadClient()

    init {
        context("Homestead") {
            context("Decorations") {
                ApiLanguage.entries.forEach { language ->
                    expect("Fetch decorations in $language language") {
                        val decorationIds = client.getDecorationIds().randomElements(100)
                        shouldNotThrowAny { client.getDecorations(decorationIds, language) }
                    }
                    expect("Fetch paged decorations in $language language") {
                        shouldNotThrowAny { client.getPagedDecorations(PageRequest(0, 10), language) }
                    }
                }
                context("Categories") {
                    ApiLanguage.entries.forEach { language ->
                        expect("Fetch categories in $language language") {
                            val categoryIds = client.getDecorationCategoryIds()
                            shouldNotThrowAny { client.getDecorationCategories(categoryIds, language) }
                        }
                        expect("Fetch paged categories in $language language") {
                            shouldNotThrowAny { client.getPagedDecorationCategories(PageRequest(0, 10), language) }
                        }
                    }
                }
            }
            context("Glyphs") {
                expect("Fetch glyphs") {
                    val glyphIds = client.getGlyphIds()
                    shouldNotThrowAny { client.getGlyphs(glyphIds) }
                }
                expect("Fetch paged glyphs") {
                    shouldNotThrowAny { client.getPagedGlyphs(PageRequest(0, 10)) }
                }
            }
        }
    }
}