package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe

internal class RaidClientSpec : BaseWiremockTest() {

    private val raidClient = GWRaidsClient()

    init {
        should("Get raids") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/raids?ids=all", "/responses/gamemechanics/raids.json", language = lang)

            // when
            val raids = raidClient.getRaids(lang)

            // then
            assertSoftly(raids[0]) {
                id shouldBe "forsaken_thicket"
                assertSoftly(wings[0]) {
                    id shouldBe "spirit_vale"
                    assertSoftly(events[0]) {
                        id shouldBe "vale_guardian"
                        type shouldBe "Boss"
                    }
                }
            }
        }
    }
}
