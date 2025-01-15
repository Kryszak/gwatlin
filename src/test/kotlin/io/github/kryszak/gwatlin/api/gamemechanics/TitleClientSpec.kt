package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class TitleClientSpec : BaseWiremockTest() {

    private val titleClient = GWTitlesClient()

    init {
        should("Get titles") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/titles?ids=all", "/responses/gamemechanics/titles.json", language = lang)

            // when
            val titles = titleClient.getTitles(lang)

            // then
            assertSoftly(titles[0]) {
                id shouldBe 1
                name shouldBe "Traveler"
                achievement shouldBe 111
                achievements shouldContainExactly listOf(111)
            }
        }
    }
}
