package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class EmotesClientSpec : BaseWiremockTest() {

    private val client = GWEmotesClient()

    init {
        should("Get emote ids") {
            // given
            stubResponse("/v2/emotes", "/responses/gamemechanics/emotes/emote_ids.json")

            // when
            val emoteIds = client.getEmoteIds()

            // then
            emoteIds shouldContainExactly listOf(
                "Bless",
                "Heroic",
                "Paper",
                "Possessed",
                "Rock",
                "Scissors",
                "Shiverplus",
                "Stretch",
                "geargrind",
                "playdead",
                "rockout",
                "shiver",
                "shuffle",
                "step"
            )
        }

        should("Get emote") {
            // given
            val emoteId = "rockout"
            stubResponse("/v2/emotes/$emoteId", "/responses/gamemechanics/emotes/emote.json")

            // when
            val emote = client.getEmote(emoteId)

            // then
            assertSoftly(emote) {
                id shouldBe emoteId
                commands shouldContainExactly listOf("/rockout", "/hardrock", "/Abrocken", "/rockear")
                unlockItems shouldContainExactly listOf(91956)
            }
        }

        should("Get emotes") {
            // given
            val emoteIds = listOf("geargrind", "shuffle")
            stubResponse("/v2/emotes?ids=${emoteIds.joinToString(",")}", "/responses/gamemechanics/emotes/emotes.json")

            // when
            val emotes = client.getEmotes(emoteIds)

            // then
            assertSoftly(emotes) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe emoteIds[0]
                    commands shouldContainExactly listOf("/geargrind", "/coureur", "/Endlos", "/corredor")
                    unlockItems shouldContainExactly listOf(20312)
                }
                assertSoftly(it[1]) {
                    id shouldBe emoteIds[1]
                    commands shouldContainExactly listOf("/shuffle", "/mezclador")
                    unlockItems shouldContainExactly listOf(20312)
                }
            }
        }

        should("Get all emotes") {
            // given
            stubResponse("/v2/emotes?ids=all", "/responses/gamemechanics/emotes/emotes_all.json")

            // when
            val emotes = client.getAllEmotes()

            // then
            assertSoftly(emotes[10]) {
                id shouldBe "rockout"
                commands shouldContainExactly listOf("/rockout", "/hardrock", "/Abrocken", "/rockear")
                unlockItems shouldContainExactly listOf(91956)
            }
        }
    }
}
