package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class JadeBotsClientTest : BaseWiremockTest() {

    private val jadeBotsClient = GWJadeBotsClient()

    init {
        should("Get jade bot ids") {
            // given
            stubResponse("/v2/jadebots", "/responses/wardrobe/jadebot/jadebot_ids.json")

            // when
            val jadeBotIds = jadeBotsClient.getJadeBotIds()

            // then
            jadeBotIds shouldHaveSize 5
        }

        should("Get jade bot") {
            // given
            val jadeBotId = 2
            stubResponse("/v2/jadebots/$jadeBotId", "/responses/wardrobe/jadebot/jadebot.json")

            // when
            val jadeBot = jadeBotsClient.getJadeBot(jadeBotId)

            // then
            assertSoftly(jadeBot) {
                id shouldBe jadeBotId
                name shouldBe "Cuddly Cat"
                description shouldBe "<c=@reminder>This is only available from the Black Lion Trading Company during limited-time sales.</c>"
                unlockItem shouldBe 98311
            }
        }

        should("Get jade bots") {
            // given
            val jadeBotIds = listOf(3,4)
            stubResponse("/v2/jadebots?ids=${jadeBotIds.joinToString(",")}","/responses/wardrobe/jadebot/jadebots.json")

            // when
            val jadeBots = jadeBotsClient.getJadeBots(jadeBotIds)

            // then
            assertSoftly(jadeBots) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe jadeBotIds[0]
                    name shouldBe "Candlewick Sprite"
                    description shouldBe "Found only in Black Lion Chests during specific times of the year."
                    unlockItem shouldBe 98882
                }
                assertSoftly(it[1]) {
                    id shouldBe jadeBotIds[1]
                    name shouldBe "Playful Ghost"
                    description shouldBe "Found only in Black Lion Chests during specific times of the year."
                    unlockItem shouldBe 99005
                }
            }
        }
    }
}
