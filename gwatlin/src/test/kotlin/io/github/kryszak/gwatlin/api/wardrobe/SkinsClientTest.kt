package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class SkinsClientTest : BaseWiremockTest() {

    private val skinsClient = GWSkinsClient()

    init {
        should("Get skin ids") {
            // given
            stubResponse("/v2/skins", "/responses/wardrobe/skin/skin_ids.json")

            // when
            val skinIds = skinsClient.getSkinIds()

            // then
            skinIds shouldHaveSize 5917
        }

        should("Get skin") {
            // given
            val id = 10
            val lang = ApiLanguage.EN

            stubResponse("/v2/skins?ids=10", "/responses/wardrobe/skin/skin.json", language = lang)

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

        should("Get skin without icon") {
            // given
            val id = 2773
            val lang = ApiLanguage.EN

            stubResponse("/v2/skins?ids=2773", "/responses/wardrobe/skin/skin_without_icon.json", language = lang)

            // when
            val skins = skinsClient.getSkins(listOf(id), lang)

            // then
            assertSoftly(skins[0]) {
                id shouldBe 2773
                type shouldBe "Weapon"
                flags.shouldBeEmpty()
                restrictions.shouldBeEmpty()
                rarity shouldBe "Basic"
                icon.shouldBeNull()
            }
        }

        should("Get paged skins") {
            // given
            stubResponse(
                "/v2/skins?page=0&page_size=10",
                "/responses/wardrobe/skin/skins_paged.json",
                pageParams = PageParameters(10, 947, 10, 9463)
            )

            // when
            val pagedSkins = skinsClient.getPagedSkins(PageRequest(0, 10))

            // then
            assertSoftly(pagedSkins) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 947
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 9463
            }
        }
    }
}