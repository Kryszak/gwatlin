package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class ColorClientTest : BaseWiremockTest() {

    private val colorClient = GWColorsClient()

    init {
        should("Get all dye colors") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/colors?ids=all", "/responses/gamemechanics/colors/dye_colors_all.json", language = lang)

            // when
            val colors = colorClient.getAllDyeColors(lang)

            // then
            assertSoftly(colors[1]) {
                id shouldBe 2
                name shouldBe "Black"
                baseRgb shouldContainExactly listOf(128, 26, 26)
                assertSoftly(cloth) {
                    brightness shouldBe -13
                    contrast shouldBe 1
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.09375
                    rgb shouldContainExactly listOf(37, 35, 38)
                }
                fur.shouldNotBeNull()
                item shouldBe 20358
                categories shouldContainExactly listOf("Gray", "Metal", "Rare")
            }
        }
        should("Get color ids") {
            // given
            stubResponse("/v2/colors", "/responses/gamemechanics/colors/dye_color_ids.json")

            // when
            val colorIds = colorClient.getDyeColorIds()

            // then
            colorIds shouldHaveSize 643
        }
        should("Get dye color") {
            // given
            val colorId = 5
            stubResponse("/v2/colors/$colorId", "/responses/gamemechanics/colors/dye_color.json")

            // when
            val color = colorClient.getDyeColor(colorId)

            // then
            assertSoftly(color) {
                id shouldBe colorId
                name shouldBe "Pitch"
                baseRgb shouldContainExactly listOf(128, 26, 26)
                assertSoftly(cloth) {
                    brightness shouldBe -8
                    contrast shouldBe 1
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.09375
                    rgb shouldContainExactly listOf(48, 46, 49)
                }
                assertSoftly(leather) {
                    brightness shouldBe -8
                    contrast shouldBe 1
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.09375
                    rgb shouldContainExactly listOf(48, 46, 49)
                }
                assertSoftly(metal) {
                    brightness shouldBe -8
                    contrast shouldBe 1.09375
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.17188
                    rgb shouldContainExactly listOf(44, 42, 45)
                }
                assertSoftly(fur!!) {
                    brightness shouldBe -8
                    contrast shouldBe 1
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.09375
                    rgb shouldContainExactly listOf(48, 46, 49)
                }
                item shouldBe 20366
                categories shouldContainExactly listOf("Gray", "Metal", "Uncommon")
            }
        }
        should("Get dye colors") {
            // given
            val ids = listOf(1,2,3)
            stubResponse("/v2/colors?ids=${ids.joinToString(",")}", "/responses/gamemechanics/colors/dye_colors.json")

            // when
            val colors = colorClient.getDyeColors(ids)

            // then
            assertSoftly(colors) {
                it shouldHaveSize 3
                assertSoftly(it[0]) {
                    id shouldBe ids[0]
                    name shouldBe "Dye Remover"
                }
                assertSoftly(it[1]) {
                    id shouldBe ids[1]
                    name shouldBe "Black"
                }
                assertSoftly(it[2]) {
                    id shouldBe ids[2]
                    name shouldBe "Chalk"
                }
            }
        }

        should("Get paged dye colors") {
            // given
            stubResponse(
                "/v2/colors?page=0&page_size=10",
                "/responses/gamemechanics/colors/dye_color_paged.json",
                pageParams = PageParameters(10, 65, 10, 643)
            )

            // when
            val pagedColors = colorClient.getPagedColors(PageRequest(0, 10))

            // then
            assertSoftly(pagedColors) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 65
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 643
            }
        }
    }
}