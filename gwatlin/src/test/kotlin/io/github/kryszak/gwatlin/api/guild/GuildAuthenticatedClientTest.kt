package io.github.kryszak.gwatlin.api.guild

import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.guild.model.log.*
import io.github.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class GuildAuthenticatedClientTest : BaseWiremockTest() {

    private val validApiKey = "1234"

    private val guildId = "4BBB52AA-D768-4FC6-8EDE-C299F2822F0F"

    private val guildAuthClient = GWGuildAuthenticatedClient(validApiKey)

    init {
        context("Should get guild logs") {
            withData(
                mapOf(
                    "without since" to GuildLogTestInput(
                        "",
                        listOf(
                            expectedGuildLogWithoutSince(),
                            expectedGuildLogRankChange(),
                            expectedGuildJoin(),
                            expectedGuildKick()
                        ),
                        stubGuildLogResponseWithoutSince()
                    ),
                    "since 1285" to GuildLogTestInput(
                        "1285", listOf(expectedGuildLogWithSince()), stubGuildLogResponseWithSince()
                    )
                )
            ) { (since, expectedGuildLog, stubbing) ->
                // given
                stubbing()

                // when
                val guildLogs = guildAuthClient.getGuildLog(guildId, since)
                guildLogs.forEach(::println)

                // then
                guildLogs shouldContainAll expectedGuildLog
            }
        }

        should("Throw exception on wrong api key") {
            // given
            val apiKey = "123"

            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log",
                "/responses/guild/guild_log_unauthenticated.json",
                apiKey = "123",
                responseStatus = 401
            )

            // when
            val exception =
                shouldThrow<ApiRequestException> { GuildAuthenticatedClient(apiKey).getGuildLog(guildId, "") }

            // then
            exception.message shouldBe "RetrieveError(text=Invalid access token)"
        }

        should("Get guild members") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/members",
                "/responses/guild/members.json",
                apiKey = validApiKey
            )

            // when
            val members = guildAuthClient.getGuildMembers(guildId)

            // then
            assertSoftly(members[0]) {
                name shouldBe "Lawton Campbell.9413"
                rank shouldBe "Leader"
                joined shouldBe "2015-07-22T06:18:35.000Z"
            }
        }

        should("Get guild ranks") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/ranks",
                "/responses/guild/ranks.json",
                apiKey = validApiKey
            )

            // when
            val ranks = guildAuthClient.getGuildRanks(guildId)

            // then
            assertSoftly(ranks[0]) {
                id shouldBe "Leader"
                order shouldBe 1
                permissions shouldHaveSize 3
                icon shouldBe "..."
            }
        }

        should("Get guild stash") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/stash",
                "/responses/guild/stash.json",
                apiKey = validApiKey
            )

            // when
            val stash = guildAuthClient.getGuildStash(guildId)

            // then
            assertSoftly(stash[0]) {
                upgradeId shouldBe 58
                size shouldBe 100
                coins shouldBe 100039
                note shouldBe "put extra siege/seaweed here please"
                assertSoftly(inventory) {
                    it shouldHaveSize 6
                    assertSoftly(it[2]!!) {
                        id shouldBe 12138
                        count shouldBe 250
                    }
                }
            }
        }

        should("Get guild treasury") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/treasury",
                "/responses/guild/treasury.json",
                apiKey = validApiKey
            )

            // when
            val treasury = guildAuthClient.getGuildTreasury(guildId)

            // then
            assertSoftly(treasury[0]) {
                itemId shouldBe 12138
                count shouldBe 200
                assertSoftly(neededBy) {
                    it shouldHaveSize 1
                    assertSoftly(it[0]) {
                        upgradeId shouldBe 42
                        count shouldBe 1000
                    }
                }
            }
        }

        should("Get guild teams") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/teams",
                "/responses/guild/teams.json",
                apiKey = validApiKey
            )

            // when
            val teams = guildAuthClient.getGuildTeams(guildId)

            // then
            assertSoftly(teams[0]) {
                id shouldBe 1
                members shouldHaveSize 2
                name shouldBe "1v1 Me Bro"
                assertSoftly(members[0]) {
                    name shouldBe "Lawton.9413"
                    role shouldBe "Captain"
                }
                assertSoftly(aggregate) {
                    wins shouldBe 3
                    losses shouldBe 0
                    desertions shouldBe 0
                    byes shouldBe 0
                    forfeits shouldBe 0
                }
                assertSoftly(ladders.unranked) {
                    wins shouldBe 3
                    losses shouldBe 0
                    desertions shouldBe 0
                    byes shouldBe 0
                    forfeits shouldBe 0
                }
                assertSoftly(games[0]) {
                    id shouldBe "98EBE4DC-E36B-4A6C-A38C-A5B898AF53A4"
                    mapId shouldBe 549
                    started shouldBe "2015-08-29T13:42:45.000Z"
                    ended shouldBe "2015-08-29T13:53:49.000Z"
                    result shouldBe "Victory"
                    team shouldBe "Blue"
                    ratingType shouldBe "Ranked"
                    assertSoftly(scores) {
                        red shouldBe 344
                        blue shouldBe 500
                    }
                }
                assertSoftly(seasons[0]) {
                    id shouldBe "44B85826-B5ED-4890-8C77-82DDF9F2CF2B"
                    wins shouldBe 1
                    losses shouldBe 0
                    rating shouldBe 1437
                }
            }
        }

        should("Get guild upgrade ids") {
            // given
            stubResponse(
                "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/upgrades",
                "/responses/guild/guild_upgrade_ids.json",
                apiKey = validApiKey
            )

            // when
            val upgradeIds = guildAuthClient.getGuildUpgrades(guildId)

            // then
            upgradeIds shouldHaveSize 3
        }
    }

    private fun stubGuildLogResponseWithSince(): () -> Unit = {
        stubResponse(
            "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log?since=1285",
            "/responses/guild/guild_log_since.json",
            apiKey = validApiKey
        )
    }

    private fun stubGuildLogResponseWithoutSince(): () -> Unit = {
        stubResponse(
            "/v2/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log",
            "/responses/guild/guild_log.json",
            apiKey = validApiKey
        )
    }

    private fun expectedGuildLogWithoutSince() =
        GuildLogTreasury(1190, "2015-12-10T23:58:49.106Z", "Lawton Campbell.9413", 24299, 150)

    private fun expectedGuildLogWithSince() =
        GuildLogUpgrade(1286, "2015-12-23T00:48:20.539Z", "Lawton Campbell.9413", UpgradeAction.QUEUED, 364)

    private fun expectedGuildLogRankChange() = GuildLogRankChange(12861, "2015-12-23T00:48:20.539Z", "Lawton Campbell.9413", "Lawton Campbell.9413", "OldRank", "NewRank")

    private fun expectedGuildJoin() = GuildLogJoined(12862, "2015-12-10T23:58:49.106Z", "Lawton Campbell.9413")

    private fun expectedGuildKick() =
        GuildLogKicked(12863, "2015-12-10T23:58:49.106Z", "Lawton Campbell.9413", "Lawton Campbell.9412")

    data class GuildLogTestInput(
        val since: String,
        val expectedGuildLogs: List<GuildLog>,
        val stubbing: () -> Unit,
    )
}