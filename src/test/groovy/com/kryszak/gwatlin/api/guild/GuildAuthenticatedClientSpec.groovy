package com.kryszak.gwatlin.api.guild

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.guild.model.GuildMember
import com.kryszak.gwatlin.api.guild.model.GuildRank
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.api.guild.model.stash.GuildStash
import com.kryszak.gwatlin.api.guild.model.team.GuildTeam
import com.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
import com.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient
import spock.lang.Subject
import spock.lang.Unroll

class GuildAuthenticatedClientSpec extends GuildStubs {

    def GUILD_ID = "4BBB52AA-D768-4FC6-8EDE-C299F2822F0F"

    @Subject
    def guildAuthClient = new GWGuildAuthenticatedClient("1234")

    @Unroll
    def "Should get guild log #description"() {
        given: "External api is stubbed"
        stub

        when: "Requesting guild log"
        def guildLogs = guildAuthClient.getGuildLog(GUILD_ID, since)

        then: "Retrieved list matches expected"
        guildLogs == parseGuildLog(expected)

        where:
        description  | since  | stub                        | expected
        ""           | ""     | stubGuildLogFullResponse()  | "guild_log.json"
        "since 1285" | "1285" | stubGuildLogSinceResponse() | "guild_log_since.json"
    }

    def "Should throw exception on wrong api key"() {
        given: "Wrong api key"
        def apiKey = "123"

        and: "External api is stubbed"
        stubGuildLogUnauthenticatedResponse()

        when: "Requesting guild log"
        new GuildAuthenticatedClient(apiKey).getGuildLog(GUILD_ID, "")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should retrieve guild members"() {
        given: "External api is stubbed"
        stubGuildMembersResponse()

        when: "Requesting guild members"
        def members = guildAuthClient.getGuildMembers(GUILD_ID)

        then: "Retrieved list matches expected"
        members == parseGuildMembers()
    }

    def "Should retrieve guild ranks"() {
        given: "External api is stubbed"
        stubGuildRanksResponse()

        when: "Requesting guild ranks"
        def ranks = guildAuthClient.getGuildRanks(GUILD_ID)

        then: "Retrieved list matches expected"
        ranks == parseRanks()
    }

    def "Should retrieve guild stash"() {
        given: "External api is stubbed"
        stubGuildStashResponse()

        when: "Requesting guild stash"
        def stash = guildAuthClient.getGuildStash(GUILD_ID)

        then: "Retrieved list matches expected"
        stash == parseStash()
    }

    def "Should retrieve guild treasury"() {
        given: "External api is stubbed"
        stubGuildTreasuryResponse()

        when: "Requesting guild treasury"
        def treasury = guildAuthClient.getGuildTreasury(GUILD_ID)

        then: "Retrieved treasury matches expected"
        treasury == parseTreasury()
    }

    def "Should retrieve guild teams"() {
        given: "External api is stubbed"
        stubGuildTeamsResponse()

        when: "Guild teams are requested"
        def teams = guildAuthClient.getGuildTeams(GUILD_ID)

        then: "Retrieved teams matches expected"
        teams == parseTeams()
    }

    def "Should retrieve guild upgrade ids"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/guild/guild_upgrade_ids.json")

        and: "External api is stubbed"
        stubGuildUpgradesResponse()

        when: "Guild upgrade ids are requested"
        def upgradeIds = guildAuthClient.getGuildUpgrades(GUILD_ID)

        then: "Retrieved list matches expected"
        upgradeIds == ids
    }

    private List<GuildTeam> parseTeams() {
        gson.fromJson(parseResponseText("/responses/guild/teams.json"),
                new TypeToken<List<GuildTeam>>() {}.getType())
    }

    private List<GuildTreasury> parseTreasury() {
        gson.fromJson(parseResponseText("/responses/guild/treasury.json"),
                new TypeToken<List<GuildTreasury>>() {}.getType())
    }

    private List<GuildStash> parseStash() {
        gson.fromJson(parseResponseText("/responses/guild/stash.json"),
                new TypeToken<List<GuildStash>>() {}.getType())
    }

    private List<GuildRank> parseRanks() {
        gson.fromJson(parseResponseText("/responses/guild/ranks.json"),
                new TypeToken<List<GuildRank>>() {}.getType())
    }

    private List<GuildMember> parseGuildMembers() {
        gson.fromJson(parseResponseText("/responses/guild/members.json"),
                new TypeToken<List<GuildMember>>() {}.getType())
    }

    private List<GuildLog> parseGuildLog(String file) {
        gson.fromJson(parseResponseText("/responses/guild/$file"),
                new TypeToken<List<GuildLog>>() {}.getType())
    }
}
