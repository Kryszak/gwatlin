package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class FinishersClientTest : BaseWiremockTest() {

    private val finishersClient = GWFinishersClient()

    init {
        should("Get finisher ids") {
            // given
            stubResponse("/finishers", "/responses/items/finisher_ids.json")

            // when
            val finisherIds = finishersClient.getFinisherIds()

            // then
            finisherIds shouldHaveSize 70
        }

        should("Get finishers") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponse("/finishers?ids=1,2", "/responses/items/finishers.json", language = lang)

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
    }
}