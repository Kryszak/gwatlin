package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class PvpAmuletsClientTest : BaseWiremockTest() {

    private val pvpAmuletClient = GWPvpAmuletsClient()

    init {
        should("Get pvp amulet ids") {
            // given
            stubResponse("/pvp/amulets", "/responses/items/pvp_amulet_ids.json")

            // when
            val amuletIds = pvpAmuletClient.getPvpAmuletIds()

            // then
            amuletIds shouldHaveSize 28
        }

        should("Get pvp amulet") {
            // given
            val ids = listOf(7)
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/pvp/amulets?ids=7", "/responses/items/pvp_amulet.json", lang)

            // when
            val pvpAmulets = pvpAmuletClient.getPvpAmulets(ids, lang)

            // then
            assertSoftly(pvpAmulets[0]) {
                id shouldBe 7
                name shouldBe "Barbarian Amulet"
                icon shouldBe "https://render.guildwars2.com/file/C1B81CDC163B21EE5824AD34B4E2640C5E340ACA/643283.png"
                assertSoftly(attributes) {
                    vitality shouldBe 1200
                    power shouldBe 900
                    precision shouldBe 900
                }
            }
        }
    }
}