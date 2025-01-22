package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class TitleClientSpec : BaseWiremockTest() {

    private val titleClient = GWTitlesClient()

    init {
        should("Get all titles") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/titles?ids=all", "/responses/gamemechanics/titles/titles_all.json", language = lang)

            // when
            val titles = titleClient.getAllTitles(lang)

            // then
            assertSoftly(titles[0]) {
                id shouldBe 1
                name shouldBe "Traveler"
                achievement shouldBe 111
                achievements shouldContainExactly listOf(111)
            }
        }
        should("Get title ids") {
            // given
            stubResponse("/v2/titles", "/responses/gamemechanics/titles/title_ids.json")

            // when
            val titleIds = titleClient.getTitleIds()

            // then
            titleIds shouldHaveSize 389
        }
        should("Get title") {
            // given
            val titleId = 1
            stubResponse("/v2/titles/$titleId", "/responses/gamemechanics/titles/title.json")

            // when
            val title = titleClient.getTitle(titleId)

            // then
            assertSoftly(title) {
                id shouldBe 1
                name shouldBe "Traveler"
                achievement shouldBe 111
                achievements shouldContainExactly listOf(111)
            }
        }
        should("Get titles") {
            // given
            val titleIds = listOf(2, 3)
            stubResponse("/v2/titles?ids=${titleIds.joinToString(",")}", "/responses/gamemechanics/titles/titles.json")

            // when
            val titles = titleClient.getTitles(titleIds)

            // then
            assertSoftly(titles) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe titleIds[0]
                    name shouldBe "Guild Warrior"
                    achievement shouldBe 112
                    achievements shouldContainExactly listOf(112)
                }
                assertSoftly(it[1]) {
                    id shouldBe titleIds[1]
                    name shouldBe "Rift Warden"
                    achievement shouldBe 113
                    achievements shouldContainExactly listOf(113)
                }
            }
        }
    }
}
