package com.kryszak.gwatlin.api.pvp

import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class PvpAuthenticatedClientTest : BaseWiremockTest() {

    private val apiKey = "1234"

    private val pvpClient = GWPvPAuthenticatedClient(apiKey)

    init {
        should("Get pvp stats") {
            // given
            stubAuthResponse("/pvp/stats", "/responses/pvp/stats.json", apiKey)

            // when
            val stats = pvpClient.getPvpStats()

            // then
            assertSoftly(stats) {
                pvpRank shouldBe 5
                pvpRankPoints shouldBe 6513
                pvpRankRollovers shouldBe 4
                aggregate.shouldNotBeNull()
                assertSoftly(professions) {
                    elementalist.shouldNotBeNull()
                    guardian.shouldNotBeNull()
                }
                assertSoftly(ladders) {
                    ranked.shouldNotBeNull()
                    unranked.shouldNotBeNull()
                }
            }
        }

        should("Get pvp game ids") {
            // given
            stubAuthResponse("/pvp/games", "/responses/pvp/account_game_ids.json", apiKey)

            // when
            val gameIds = pvpClient.getPvpGameIds()

            // then
            gameIds shouldContainExactly listOf(
                "0639D2B5-FFD8-4D5F-9BDF-29E1A4C8A73D",
                "114BCDCA-8443-4E65-BDCD-00AA4EBA2A81",
                "1AB530B8-297E-4FA5-B7BE-BA4BAC420EC3",
                "4717EE75-1DF5-4DBA-8951-C38AE3943139",
                "590AD259-8B96-46B9-BB72-AF075FE5A4CA",
                "7E66F29E-23CF-4B06-B340-8B8717461027",
                "8AF2A34A-4A2A-45BA-A2C5-9B9155623192",
                "8B50CA43-CF66-48A6-8F00-74B4C3AB0AAE",
                "E80FB755-2901-49DC-B2CE-8ABE9B200EE9",
                "F8FA9E4E-21AB-4617-B1A4-ACAC12700F58"
            )
        }

        should("Get pvp game") {
            // given
            stubAuthResponse("/pvp/games?ids=ABCDE02B-8888-FEBA-1234-DE98765C7DEF", "/responses/pvp/game.json", apiKey)

            // when
            val games = pvpClient.getPvpGames(listOf("ABCDE02B-8888-FEBA-1234-DE98765C7DEF"))

            // then
            assertSoftly(games[0]) {
                id shouldBe "ABCDE02B-8888-FEBA-1234-DE98765C7DEF"
                mapId shouldBe 894
                started shouldBe "2015-07-08T21:29:50.000Z"
                ended shouldBe "2015-07-08T21:37:02.000Z"
                result shouldBe "Defeat"
                team shouldBe "Red"
                profession shouldBe "Guardian"
                scores.shouldNotBeNull()
                ratingType shouldBe "Ranked"
                ratingChange shouldBe -26
                season shouldBe "49CCE661-9DCC-473B-B106-666FE9942721"
            }
        }

        should("Get pvp standings") {
            // given
            stubAuthResponse("/pvp/standings", "/responses/pvp/standings.json", apiKey)

            // when
            val standings = pvpClient.getPvpStanding()

            // then
            assertSoftly(standings) {
                seasonId shouldBe "44B85826-B5ED-4890-8C77-82DDF9F2CF2B"
                assertSoftly(current) {
                    totalPoints shouldBe 101
                    division shouldBe 4
                    tier shouldBe 1
                    points shouldBe 1
                    repeats shouldBe 0
                    rating shouldBe 845
                    decay shouldBe 100
                }
                assertSoftly(best) {
                    totalPoints shouldBe 200
                    division shouldBe 5
                    tier shouldBe 4
                    points shouldBe 0
                    repeats shouldBe 2
                }
            }
        }
    }
}