package com.kryszak.gwatlin.api.guild

import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.guild.model.GuildMember
import com.kryszak.gwatlin.api.guild.model.GuildRank
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.api.guild.model.stash.GuildStash
import com.kryszak.gwatlin.api.guild.model.team.GuildTeam
import com.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
import com.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient
import com.kryszak.gwatlin.config.WiremockTest
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.builtins.BuiltinSerializersKt
import spock.lang.Shared
import spock.lang.Subject
import spock.lang.Unroll

class GuildAuthenticatedClientSpec extends WiremockTest {

    @Shared
    def VALID_API_KEY = "1234"

    def GUILD_ID = "4BBB52AA-D768-4FC6-8EDE-C299F2822F0F"

    @Subject
    def guildAuthClient = new GWGuildAuthenticatedClient(VALID_API_KEY)

    @Unroll
    def "Should get guild log #description"() {
        given: "External api is stubbed"
        stub

        when: "Requesting guild log"
        def guildLogs = guildAuthClient.getGuildLog(GUILD_ID, since)

        then: "Retrieved list matches expected"
        guildLogs == parseGuildLog(expected)

        where:
        description  | since  | expected               | stub
        ""           | ""     | "guild_log.json"       | stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log", "/responses/guild/guild_log.json", VALID_API_KEY)
        "since 1285" | "1285" | "guild_log_since.json" | stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log?since=1285", "/responses/guild/guild_log_since.json", VALID_API_KEY)
    }

    def "Should throw exception on wrong api key"() {
        given: "Wrong api key"
        def apiKey = "123"

        and: "External api is stubbed"
        stubUnauthenticatedResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log", "/responses/guild/guild_log_unauthenticated.json", "123")

        when: "Requesting guild log"
        new GuildAuthenticatedClient(apiKey).getGuildLog(GUILD_ID, "")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should retrieve guild members"() {
        given: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/members", "/responses/guild/members.json", VALID_API_KEY)

        when: "Requesting guild members"
        def members = guildAuthClient.getGuildMembers(GUILD_ID)

        then: "Retrieved list matches expected"
        members == parseGuildMembers()
        verifyAll(members.get(0)) {
            name == "Lawton Campbell.9413"
            rank == "Leader"
            joined == "2015-07-22T06:18:35.000Z"
        }
    }

    def "Should retrieve guild ranks"() {
        given: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/ranks", "/responses/guild/ranks.json", VALID_API_KEY)

        when: "Requesting guild ranks"
        def ranks = guildAuthClient.getGuildRanks(GUILD_ID)

        then: "Retrieved list matches expected"
        ranks == parseRanks()
        verifyAll(ranks.get(0)) {
            id == "Leader"
            order == 1
            permissions.size() == 3
            icon == "..."
        }
    }

    def "Should retrieve guild stash"() {
        given: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/stash", "/responses/guild/stash.json", VALID_API_KEY)

        when: "Requesting guild stash"
        def stash = guildAuthClient.getGuildStash(GUILD_ID)

        then: "Retrieved list matches expected"
        stash == parseStash()
        verifyAll(stash.get(0)) {
            upgradeId == 58
            size == 100
            coins == 100039
            note == "put extra siege/seaweed here please"
            inventory.size() == 6
        }
    }

    def "Should retrieve guild treasury"() {
        given: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/treasury", "/responses/guild/treasury.json", VALID_API_KEY)

        when: "Requesting guild treasury"
        def treasury = guildAuthClient.getGuildTreasury(GUILD_ID)

        then: "Retrieved treasury matches expected"
        treasury == parseTreasury()
        verifyAll(treasury.get(0)) {
            itemId == 12138
            count == 200
            neededBy.size() == 1
        }
    }

    def "Should retrieve guild teams"() {
        given: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/teams", "/responses/guild/teams.json", VALID_API_KEY)

        when: "Guild teams are requested"
        def teams = guildAuthClient.getGuildTeams(GUILD_ID)

        then: "Retrieved teams matches expected"
        teams == parseTeams()
        verifyAll(teams.get(0)) {
            id == 1
            members.size() == 2
            name == "1v1 Me Bro"
            verifyAll(aggregate) {
                wins == 3
                losses == 0
                desertions == 0
                byes == 0
                forfeits == 0
            }
            verifyAll(ladders.unranked) {
                wins == 3
                losses == 0
                desertions == 0
                byes == 0
                forfeits == 0
            }
            verifyAll(games.get(0)) {
                id == "98EBE4DC-E36B-4A6C-A38C-A5B898AF53A4"
                mapId == 549
                started == "2015-08-29T13:42:45.000Z"
                ended == "2015-08-29T13:53:49.000Z"
                result == "Victory"
                team == "Blue"
                ratingType == "Ranked"
                verifyAll(scores) {
                    red == 344
                    blue == 500
                }
            }
        }
    }

    def "Should retrieve guild upgrade ids"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/guild/guild_upgrade_ids.json")

        and: "External api is stubbed"
        stubAuthResponse("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/upgrades", "/responses/guild/guild_upgrade_ids.json", VALID_API_KEY)

        when: "Guild upgrade ids are requested"
        def upgradeIds = guildAuthClient.getGuildUpgrades(GUILD_ID)

        then: "Retrieved list matches expected"
        upgradeIds == ids
    }

    private List<GuildTeam> parseTeams() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildTeam)),
                parseResponseText("/responses/guild/teams.json")
        ) as List<GuildTeam>
    }

    private List<GuildTreasury> parseTreasury() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildTreasury)),
                parseResponseText("/responses/guild/treasury.json")
        ) as List<GuildTreasury>
    }

    private List<GuildStash> parseStash() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildStash)),
                parseResponseText("/responses/guild/stash.json")
        ) as List<GuildStash>
    }

    private List<GuildRank> parseRanks() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildRank)),
                parseResponseText("/responses/guild/ranks.json")
        ) as List<GuildRank>
    }

    private List<GuildMember> parseGuildMembers() {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildMember)),
                parseResponseText("/responses/guild/members.json")
        ) as List<GuildMember>
    }

    private List<GuildLog> parseGuildLog(String file) {
        json.decodeFromString(
                BuiltinSerializersKt.ListSerializer(SerializersKt.serializer(GuildLog)),
                parseResponseText("/responses/guild/$file")
        ) as List<GuildLog>
    }
}
