package io.github.kryszak.gwatlin.api.miscellaneous

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class MiscellaneousClientTest : BaseWiremockTest() {

    private val miscellaneousClient = GWMiscellaneousClient()

    init {
        should("Get build id") {
            // given
            stubResponse("/v2/build", "/responses/miscellaneous/build_id.json")

            // when
            val buildId = miscellaneousClient.getBuildId()

            // then
            buildId shouldBe 45075
        }

        should("Get icons") {
            // given
            stubResponse("/v2/files?ids=all", "/responses/miscellaneous/icons.json")

            // when
            val icons = miscellaneousClient.getIcons()

            // then
            assertSoftly(icons[0]) {
                id shouldBe "map_complete"
                icon shouldBe "https://render.guildwars2.com/file/5A4E663071250EC72668C09E3C082E595A380BF7/528724.png"
            }
        }

        should("Get quaggans") {
            // given
            stubResponse("/v2/quaggans?ids=all", "/responses/miscellaneous/quaggans.json")

            // when
            val quaggans = miscellaneousClient.getQuaggans()

            // then
            quaggans shouldHaveSize 35
        }

        should("Get worlds") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/worlds?ids=all", "/responses/miscellaneous/worlds.json", language = lang)

            // when
            val worlds = miscellaneousClient.getWorlds(lang)

            // then
            assertSoftly(worlds[0]) {
                id shouldBe 1001
                name shouldBe "Anvil Rock"
                population shouldBe "High"
            }
        }
    }
}