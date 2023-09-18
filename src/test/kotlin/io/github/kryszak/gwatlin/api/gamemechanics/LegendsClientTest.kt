package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class LegendsClientTest : BaseWiremockTest() {

    private val legendsClient = GWLegendsClient()

    init {
        should("Get legend ids") {
            // given
            stubResponse("/legends", "/responses/gamemechanics/legend_ids.json")

            // when
            val legendIds = legendsClient.getLegendIds()

            // then
            legendIds shouldHaveSize 6
        }

        should("Get legends") {
            // given
            val ids = listOf("Legend1", "Legend2")
            val lang = io.github.kryszak.gwatlin.api.ApiLanguage.EN

            stubResponse("/legends?ids=Legend1,Legend2", "/responses/gamemechanics/legends.json", language = lang)

            // when
            val legends = legendsClient.getLegends(ids, lang)

            // then
            assertSoftly(legends[0]) {
                id shouldBe "Legend1"
                swap shouldBe 28229
                heal shouldBe 27220
                elite shouldBe 27760
                utilities shouldHaveSize 3
            }
        }
    }
}