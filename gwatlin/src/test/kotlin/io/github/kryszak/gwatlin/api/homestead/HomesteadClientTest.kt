package io.github.kryszak.gwatlin.api.homestead

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class HomesteadClientTest : BaseWiremockTest() {

    private val client = GWHomesteadClient()

    init {
        should("Get decoration ids") {
            // given
            stubResponse("/v2/homestead/decorations", "/responses/homestead/decorations/decoration_ids.json")

            // when
            val decorationIds = client.getDecorationIds()

            // then
            decorationIds shouldHaveSize 686
        }

        should("Get decoration") {
            // given
            val decorationId = 140
            stubResponse("/v2/homestead/decorations/$decorationId", "/responses/homestead/decorations/decoration.json")

            // when
            val decoration = client.getDecoration(decorationId)

            // then
            assertSoftly(decoration) {
                id shouldBe decorationId
                name shouldBe "Sandstone Pillar"
                description shouldBe "Crafted by scribes."
                categories shouldContainExactly listOf(16, 1)
                maxCount shouldBe 250
                icon shouldBe "https://render.guildwars2.com/file/A2DBDD1E546A2F5EDAC301BA07FEA20F1B1C12A7/1223799.png"
            }
        }

        should("Get decorations") {
            // given
            val decorationIds = listOf(120, 122)
            stubResponse(
                "/v2/homestead/decorations?ids=${decorationIds.joinToString(",")}",
                "/responses/homestead/decorations/decorations.json"
            )

            // when
            val decorations = client.getDecorations(decorationIds)

            // then
            assertSoftly(decorations) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe decorationIds[0]
                    name shouldBe "Snowman Ice Sculpture"
                    description shouldBe "Crafted by scribes."
                    categories shouldContainExactly listOf(9, 25)
                    maxCount shouldBe 250
                    icon shouldBe "https://render.guildwars2.com/file/080D0D6694717DDC710EB7F86D94699F3C35340D/1614396.png"
                }
                assertSoftly(it[1]) {
                    id shouldBe decorationIds[1]
                    name shouldBe "Thorny Mushroom"
                    description shouldBe "Crafted by scribes."
                    categories shouldContainExactly listOf(7)
                    maxCount shouldBe 250
                    icon shouldBe "https://render.guildwars2.com/file/3FD56F9553535CD33CEB4A1D08499537BC5FF306/1224808.png"
                }
            }
        }

        should("Get decoration category ids") {
            // given
            stubResponse(
                "/v2/homestead/decorations/categories",
                "/responses/homestead/decorations/categories/category_ids.json"
            )

            // when
            val categoryIds = client.getDecorationCategoryIds()

            // then
            categoryIds shouldHaveSize 26
        }

        should("Get decoration category") {
            // given
            val categoryId = 1
            stubResponse(
                "/v2/homestead/decorations/categories/$categoryId",
                "/responses/homestead/decorations/categories/category.json"
            )

            // when
            val category = client.getDecorationCategory(categoryId)

            // then
            assertSoftly(category) {
                id shouldBe categoryId
                name shouldBe "Architecture"
            }
        }

        should("Get decoration categories") {
            // given
            val categoryIds = listOf(2, 3)
            stubResponse(
                "/v2/homestead/decorations/categories?ids=${categoryIds.joinToString(",")}",
                "/responses/homestead/decorations/categories/categories.json"
            )

            // when
            val categories = client.getDecorationCategories(categoryIds)

            // then
            assertSoftly(categories) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe categoryIds[0]
                    name shouldBe "Table, Seating, Etc."
                }
                assertSoftly(it[1]) {
                    id shouldBe categoryIds[1]
                    name shouldBe "Storage"
                }
            }
        }

        should("Get glyph ids") {
            // given
            stubResponse("/v2/homestead/glyphs", "/responses/homestead/glyphs/glyph_ids.json")

            // when
            val glyphIds = client.getGlyphIds()

            // then
            glyphIds shouldHaveSize 36
        }

        should("Get glyph") {
            // given
            val glyphId = "alchemy_harvesting"
            stubResponse("/v2/homestead/glyphs/$glyphId", "/responses/homestead/glyphs/glyph.json")

            // when
            val glyph = client.getGlyph(glyphId)

            // then
            assertSoftly(glyph) {
                id shouldBe glyphId
                itemId shouldBe 90805
                slot shouldBe "harvesting"
            }
        }

        should("Get glyphs") {
            // given
            val glyphIds = listOf("alchemy_logging", "alchemy_mining")
            stubResponse(
                "/v2/homestead/glyphs?ids=${glyphIds.joinToString(",")}",
                "/responses/homestead/glyphs/glyphs.json"
            )

            // when
            val glyphs = client.getGlyphs(glyphIds)

            // then
            assertSoftly(glyphs) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe glyphIds[0]
                    itemId shouldBe 90805
                    slot shouldBe "logging"
                }
                assertSoftly(it[1]) {
                    id shouldBe glyphIds[1]
                    itemId shouldBe 90805
                    slot shouldBe "mining"
                }
            }
        }
    }
}