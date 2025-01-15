package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class ColorClientTest : BaseWiremockTest() {

    private val colorClient = GWColorsClient()

    init {
        should("Get dye colors") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/colors?ids=all", "/responses/gamemechanics/dye_colors.json", language = lang)

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
    }
}