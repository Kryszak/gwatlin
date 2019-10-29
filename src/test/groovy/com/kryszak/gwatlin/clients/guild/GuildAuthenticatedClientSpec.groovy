package com.kryszak.gwatlin.clients.guild

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import spock.lang.Subject
import spock.lang.Unroll

class GuildAuthenticatedClientSpec extends GuildStubs {

    @Subject
    def guildAuthClient = new GuildAuthenticatedClient("1234")

    @Unroll
    def "Should get guild log #description"() {
        given: "Guild id"
        def id = "4BBB52AA-D768-4FC6-8EDE-C299F2822F0F"

        and: "External api is stubbed"
        stub

        when: "Requesting guild log"
        def guildLogs = guildAuthClient.getGuildLog(id, since)

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
        new GuildAuthenticatedClient(apiKey).getGuildLog("4BBB52AA-D768-4FC6-8EDE-C299F2822F0F", "")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private List<GuildLog> parseGuildLog(String file) {
        gson.fromJson(parseResponseText("/responses/guild/$file"),
                new TypeToken<List<GuildLog>>() {}.getType())
    }
}
