package com.kryszak.gwatlin.api.guild

import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class GuildAuthenticatedClientTest : BaseWiremockTest() {

    private val validApiKey = "1234"

    private val guildId = "4BBB52AA-D768-4FC6-8EDE-C299F2822F0F"

    private val guildAuthClient = GWGuildAuthenticatedClient(validApiKey)

    init {
        should("Should get guild log (data.forAll)") {
            forAll(
                table(
                    headers("since", "expectedGuildLogs", "stubbing"),
                    row("", expectedGuildLogWithoutSince(), stubGuildLogResponseWithoutSince()),
                    row("1285", expectedGuildLogWithSince(), stubGuildLogResponseWithSince())
                )
            ) { since, expectedGuildLog, stubbing ->
                // given
                stubbing()

                // when
                val guildLogs = guildAuthClient.getGuildLog(guildId, since)

                // then
                guildLogs shouldContain expectedGuildLog
            }
        }

        should("Throw exception on wrong api key") {
            // given
            val apiKey = "123"

            stubResponse(
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log",
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
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/members",
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
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/ranks",
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
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/stash",
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
                inventory shouldHaveSize 6
            }
        }

        should("Get guild treasury") {
            // given
            stubResponse(
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/treasury",
                "/responses/guild/treasury.json",
                apiKey = validApiKey
            )

            // when
            val treasury = guildAuthClient.getGuildTreasury(guildId)

            // then
            assertSoftly(treasury[0]) {
                itemId shouldBe 12138
                count shouldBe 200
                neededBy shouldHaveSize 1
            }
        }

        should("Get guild teams") {
            // given
            stubResponse(
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/teams",
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
            }
        }

        should("Get guild upgrade ids") {
            // given
            stubResponse(
                "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/upgrades",
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
            "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log?since=1285",
            "/responses/guild/guild_log_since.json",
            apiKey = validApiKey
        )
    }

    private fun stubGuildLogResponseWithoutSince(): () -> Unit = {
        stubResponse(
            "/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log",
            "/responses/guild/guild_log.json",
            apiKey = validApiKey
        )
    }

    private fun expectedGuildLogWithoutSince() =
        GuildLog(1190, "2015-12-10T23:58:49.106Z", "Lawton Campbell.9413", "treasury")

    private fun expectedGuildLogWithSince() =
        GuildLog(1286, "2015-12-23T00:48:20.539Z", "Lawton Campbell.9413", "upgrade")
}