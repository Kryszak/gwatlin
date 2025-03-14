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
            stubResponse("/v2/legends", "/responses/gamemechanics/legends/legend_ids.json")

            // when
            val legendIds = legendsClient.getLegendIds()

            // then
            legendIds shouldHaveSize 6
        }

        should("Get legends") {
            // given
            val ids = listOf("Legend1", "Legend2")
            val lang = ApiLanguage.EN

            stubResponse("/v2/legends?ids=Legend1,Legend2", "/responses/gamemechanics/legends/legends.json", language = lang)

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