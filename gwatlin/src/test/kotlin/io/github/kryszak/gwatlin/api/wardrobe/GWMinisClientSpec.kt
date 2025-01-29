package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class GWMinisClientSpec : BaseWiremockTest() {

    private val minisClient = GWMinisClient()

    init {
        should("Get all minis") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/minis?ids=all", "/responses/wardrobe/mini/minis_all.json", language = lang)

            // when
            val minis = minisClient.getAllMinis(lang)

            // then
            assertSoftly(minis[0]) {
                id shouldBe 1
                name shouldBe "Mini Rytlock"
                icon shouldBe "https://render.guildwars2.com/file/795ED1B945A29EC3E3066797DF57FFB25ABAA631/340551.png"
                order shouldBe 1
                itemId shouldBe 21047
            }
        }

        should("Get mini ids") {
            // given
            stubResponse("/v2/minis", "/responses/wardrobe/mini/mini_ids.json")

            // when
            val miniIds = minisClient.getMiniIds()

            // then
            miniIds shouldHaveSize 902
        }

        should("Get mini") {
            // given
            val miniId = 1
            stubResponse("/v2/minis/$miniId", "/responses/wardrobe/mini/mini.json")

            // when
            val mini = minisClient.getMini(miniId)

            // then
            assertSoftly(mini) {
                id shouldBe miniId
                name shouldBe "Mini Rytlock"
                icon shouldBe "https://render.guildwars2.com/file/795ED1B945A29EC3E3066797DF57FFB25ABAA631/340551.png"
                order shouldBe 1
                itemId shouldBe 21047
            }
        }

        should("Get minis") {
            // given
            val miniIds = listOf(2, 3)
            stubResponse("/v2/minis?ids=${miniIds.joinToString(",")}", "/responses/wardrobe/mini/minis.json")

            // when
            val minis = minisClient.getMinis(miniIds)

            // then
            assertSoftly(minis) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe miniIds[0]
                    name shouldBe "Mini Servitor Golem"
                    icon shouldBe "https://render.guildwars2.com/file/B3DC031CFBF3A257A058C4FC41C8450AD510A7CD/65888.png"
                    order shouldBe 1
                    itemId shouldBe 20949
                }
                assertSoftly(it[1]) {
                    id shouldBe miniIds[1]
                    name shouldBe "Mini Rockfur Racoon"
                    icon shouldBe "https://render.guildwars2.com/file/107AF7A2AE7EC90C5EF9066A27BB7C320F977CBB/62861.png"
                    order shouldBe 1
                    itemId shouldBe 20950
                }
            }
        }
    }
}
