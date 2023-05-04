package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class PvpClientTest : BaseWiremockTest() {

    private val pvpClient = GWPvPClient()

    init {
        should("Get pvp rank ids") {
            // given
            stubResponse("/pvp/ranks", "/responses/pvp/rank_ids,json")

            // when
            val rankIds = pvpClient.getPvpRankIds()

            // then
            rankIds shouldHaveSize 9
        }

        should("Get pvp ranks") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/pvp/ranks?ids=1,2", "/responses/pvp/ranks.json", lang)

            // when
            val ranks = pvpClient.getPvpRanks(ids, lang)

            // then
            assertSoftly(ranks[0]) {
                id shouldBe 1
                finisherId shouldBe 1
                name shouldBe "Rabbit"
                icon shouldBe "https://render.guildwars2.com/file/592A4144FE1B6904CD0C69230840B8C21A0C36F7/347222.png"
                minRank shouldBe 1
                maxRank shouldBe 9
                assertSoftly(levels[0]) {
                    minRank shouldBe 1
                    maxRank shouldBe 5
                    points shouldBe 500
                }
            }
        }

        should("Get pvp season ids") {
            // given
            stubResponse("/pvp/seasons", "/responses/pvp/season_ids.json")

            // when
            val seasonIds = pvpClient.getPvpSeasonIds()

            // then
            seasonIds shouldHaveSize 18
        }

        should("Get pvp seasons") {
            // given
            val ids = listOf("44B85826-B5ED-4890-8C77-82DDF9F2CF2B", "95D5B290-798A-421E-A919-1C2A75F74B72")
            val lang = ApiLanguage.EN

            stubResponseWithLanguage(
                "/pvp/seasons?ids=44B85826-B5ED-4890-8C77-82DDF9F2CF2B,95D5B290-798A-421E-A919-1C2A75F74B72",
                "/responses/pvp/seasons.json", lang
            )

            // when
            val seasons = pvpClient.getPvpSeasons(ids, lang)

            // then
            assertSoftly(seasons[0]) {
                id shouldBe "44B85826-B5ED-4890-8C77-82DDF9F2CF2B"
                name shouldBe "PvP League Season One"
                start shouldBe "2015-12-01T20:00:00.000Z"
                end shouldBe "2016-01-28T01:00:00.000Z"
                !active
                assertSoftly(divisions[0]) {
                    name shouldBe "Division 1: Amber"
                    flags shouldHaveSize 0
                    largeIcon shouldBe "https://render.guildwars2.com/file/02ED75461164551455297DA4955862552C2452BE/1313334.png"
                    smallIcon shouldBe "https://render.guildwars2.com/file/6357FE56301B2F4AD1F309E62739B0110DA2452A/1313340.png"
                    pipIcon shouldBe "https://render.guildwars2.com/file/47BDF237FF800552EDD69D28BC926031FC4B64A9/1313346.png"
                    assertSoftly(tiers[0]) {
                        points shouldBe 5
                    }
                }
                assertSoftly(leaderboards.legendary) {
                    assertSoftly(settings) {
                        name shouldBe ""
                        duration shouldBe 0
                        scoring shouldBe "E6487336-4B5B-4BFA-9CFA-9FF232CAEF85"
                        assertSoftly(tiers[0]) {
                            range shouldHaveSize 2
                        }
                    }
                    assertSoftly(scorings[0]) {
                        id shouldBe "E6487336-4B5B-4BFA-9CFA-9FF232CAEF85"
                        type shouldBe "Integer"
                        description shouldBe "Current prestige rank. Prestige rank can be gained or lost by winning or losing ranked matches in the legendary division."
                        name shouldBe "Prestige"
                        ordering shouldBe "MoreIsBetter"
                    }
                }
            }
        }
    }
}