package io.github.kryszak.gwatlin.api.pvp

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class PvpAmuletsClientTest : BaseWiremockTest() {

    private val pvpAmuletClient = GWPvpAmuletsClient()

    init {
        should("Get pvp amulet ids") {
            // given
            stubResponse("/v2/pvp/amulets", "/responses/pvp/pvp_amulet_ids.json")

            // when
            val amuletIds = pvpAmuletClient.getPvpAmuletIds()

            // then
            amuletIds shouldHaveSize 28
        }

        should("Get pvp amulet") {
            // given
            val ids = listOf(7)
            val lang = ApiLanguage.EN

            stubResponse("/v2/pvp/amulets?ids=7", "/responses/pvp/pvp_amulet.json", language = lang)

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

        should("Get all pvp amulet attributes") {
            // given
            val ids = listOf(8, 9, 30, 31)
            stubResponse("/v2/pvp/amulets?ids=${ids.joinToString(",")}", "/responses/pvp/pvp_amulets_attributes.json")

            // when
            val pvpAmulets = pvpAmuletClient.getPvpAmulets(ids)

            // then
            assertSoftly(pvpAmulets) {
                assertSoftly(it[0].attributes) {
                    power shouldBe 1200
                    precision shouldBe 900
                    critDamage shouldBe 900
                }
                assertSoftly(it[1].attributes) {
                    conditionDamage shouldBe 1200
                    power shouldBe 900
                    vitality shouldBe 900
                }
                assertSoftly(it[2].attributes) {
                    power shouldBe 1000
                    precision shouldBe 1000
                    toughness shouldBe 400
                    vitality shouldBe 400
                }
                assertSoftly(it[3].attributes) {
                    power shouldBe 1000
                    conditionDamage shouldBe 1000
                    vitality shouldBe 500
                    healing shouldBe 500
                }
            }
        }
    }
}