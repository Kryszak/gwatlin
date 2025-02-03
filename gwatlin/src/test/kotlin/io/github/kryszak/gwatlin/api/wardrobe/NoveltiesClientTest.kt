package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class NoveltiesClientTest : BaseWiremockTest() {

    private val noveltiesClient = GWNoveltiesClient()

    init {
        should("Get all novelties") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/novelties?ids=all", "/responses/wardrobe/novelties/novelties_all.json", language = lang)

            // when
            val novelties = noveltiesClient.getAllNovelties(lang)

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

        should("Get novelty ids") {
            // given
            stubResponse("/v2/novelties", "/responses/wardrobe/novelties/novelty_ids.json")

            // when
            val noveltyIds = noveltiesClient.getNoveltyIds()

            // then
            noveltyIds shouldHaveSize 228
        }

        should("Get novelty") {
            // given
            val noveltyId = 1
            stubResponse("/v2/novelties/$noveltyId", "/responses/wardrobe/novelties/novelty.json")

            // when
            val novelty = noveltiesClient.getNovelty(noveltyId)

            // then
            assertSoftly(novelty) {
                id shouldBe noveltyId
                name shouldBe "Embellished Kite"
                description shouldBe "<c=@abilitytype>Held Item.</c> Equip a bundle for decoration or to use noncombat skills."
                icon shouldBe "https://render.guildwars2.com/file/7B043D640ED57517051D5FC038D7CDDDE5F82933/2015154.png"
                slot shouldBe "HeldItem"
                unlockItem shouldContainExactly listOf(88124)
            }
        }

        should("Get novelties") {
            // given
            val noveltyIds= listOf(2,3)
            stubResponse("/v2/novelties?ids=${noveltyIds.joinToString(",")}", "/responses/wardrobe/novelties/novelties.json")

            // when
            val novelties = noveltiesClient.getNovelties(noveltyIds)

            // then
            assertSoftly(novelties) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe noveltyIds[0]
                    name shouldBe "Musical Bass Guitar"
                    description shouldBe "<c=@abilitytype>Musical Instrument.</c> Equip an instrument and play a tune."
                    icon shouldBe "https://render.guildwars2.com/file/2FD55ABA480D23E3740145B5802800554245DB11/960182.png"
                    slot shouldBe "Music"
                    unlockItem shouldContainExactly listOf(36174)
                }
                assertSoftly(it[1]) {
                    id shouldBe noveltyIds[1]
                    name shouldBe "Duskk's World 2 Super Boom Box"
                    description shouldBe "<c=@abilitytype>Musical Instrument.</c> Equip an instrument and play a tune."
                    icon shouldBe "https://render.guildwars2.com/file/49126005BA2E197E5A60F9CFDA530EE432307776/1664072.png"
                    slot shouldBe "Music"
                    unlockItem shouldContainExactly listOf(80932)
                }
            }
        }
    }
}
