package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe

internal class DungeonsClientSpec : BaseWiremockTest() {

    private val dungeonsClient = GWDungeonsClient()

    init {
        should("Get dungeons") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/dungeons?ids=all", "/responses/gamemechanics/dungeons.json", language = lang)

            // when
            val dungeons = dungeonsClient.getDungeons(lang)

            // then
            assertSoftly(dungeons[0]) {
                id shouldBe "ascalonian_catacombs"
                assertSoftly(paths[0]) {
                    id shouldBe "ac_story"
                    type shouldBe "Story"
                }
            }
        }
    }
}
