package io.github.kryszak.gwatlin.api.miscellaneous

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
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

        should("Get minis") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/minis?ids=all", "/responses/miscellaneous/minis.json", language = lang)

            // when
            val minis = miscellaneousClient.getMinis(lang)

            // then
            assertSoftly(minis[0]) {
                id shouldBe 1
                name shouldBe "Mini Rytlock"
                icon shouldBe "https://render.guildwars2.com/file/795ED1B945A29EC3E3066797DF57FFB25ABAA631/340551.png"
                order shouldBe 1
                itemId shouldBe 21047
            }
        }

        should("Get novelties") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/novelties?ids=all", "/responses/miscellaneous/novelties.json", language = lang)

            // when
            val novelties = miscellaneousClient.getNovelties(lang)

            // then
            assertSoftly(novelties[0]) {
                id shouldBe 1
                name shouldBe "Embellished Kite"
                description shouldBe "<c=@abilitytype>Held Item.</c> Equip a bundle for decoration or to use noncombat skills."
                icon shouldBe "https://render.guildwars2.com/file/7B043D640ED57517051D5FC038D7CDDDE5F82933/2015154.png"
                slot shouldBe "HeldItem"
                unlockItem shouldContainExactly listOf(88124)
            }
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