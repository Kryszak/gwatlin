package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class SkinsClientTest : BaseWiremockTest() {

    private val skinsClient = GWSkinsClient()

    init {
        should("Get skin ids") {
            // given
            stubResponse("/v2/skins", "/responses/items/skin_ids.json")

            // when
            val skinIds = skinsClient.getSkinIds()

            // then
            skinIds shouldHaveSize 5917
        }

        should("Get skin") {
            // given
            val id = 10
            val lang = ApiLanguage.EN

            stubResponse("/v2/skins?ids=10", "/responses/items/skin.json", language = lang)

            // when
            val skins = skinsClient.getSkins(listOf(id), lang)

            // then
            assertSoftly(skins[0]) {
                id shouldBe 10
                type shouldBe "Armor"
                flags shouldHaveSize 1
                restrictions.shouldBeEmpty()
                rarity shouldBe "Basic"
                icon shouldBe "https://render.guildwars2.com/file/1920ACA302E656B60C38605521760351F147809D/61088.png"
            }
        }
    }
}