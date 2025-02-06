package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class FinishersClientTest : BaseWiremockTest() {

    private val finishersClient = GWFinishersClient()

    init {
        should("Get finisher ids") {
            // given
            stubResponse("/v2/finishers", "/responses/wardrobe/finisher/finisher_ids.json")

            // when
            val finisherIds = finishersClient.getFinisherIds()

            // then
            finisherIds shouldHaveSize 70
        }

        should("Get finishers") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponse("/v2/finishers?ids=1,2", "/responses/wardrobe/finisher/finishers.json", language = lang)

            // when
            val finishers = finishersClient.getFinishers(ids, lang)

            // then
            assertSoftly(finishers[0]) {
                id shouldBe 1
                unlockDetails shouldBe "<c=@reminder>Unlock this PvP rank finisher by earning rank points and increasing your PvP rank.</c>"
                unlockItems.shouldBeEmpty()
                order shouldBe 18
                icon shouldBe "https://render.guildwars2.com/file/807516C20D08B908946167EADD57980163EECA4E/620101.png"
                name shouldBe "Rabbit Rank Finisher"
            }
        }

        should("Get paged finishers") {
            // given
            stubResponse(
                "/v2/finishers?page=0&page_size=10",
                "/responses/wardrobe/finisher/finishers_paged.json",
                pageParams = PageParameters(10, 7, 10, 70)
            )

            // when
            val pagedFinishers = finishersClient.getPagedFinishers(PageRequest(0, 10))

            // then
            assertSoftly(pagedFinishers) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 7
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 70
            }
        }
    }
}