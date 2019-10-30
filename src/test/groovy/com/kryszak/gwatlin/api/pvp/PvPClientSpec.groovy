package com.kryszak.gwatlin.api.pvp

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
import com.kryszak.gwatlin.api.pvp.model.season.PvpSeason
import spock.lang.Subject

class PvPClientSpec extends PvpStubs {

    @Subject
    def pvpClient = new GWPvPClient()

    def "Should get pvp rank ids"() {
        given: "Expected rank ids"
        def ids = parseResponse("/responses/pvp/rank_ids,json")

        and: "External api is stubbed"
        stubPvpRankIdsResponse()

        when: "Requesting rank ids"
        def rankIds = pvpClient.getPvpRankIds()

        then: "Retrieved list matches expected"
        rankIds == ids
    }

    def "Should get pvp ranks"() {
        given: "Pvp rank ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubPvpRanksResponse()

        when: "Requesting pvp ranks"
        def ranks = pvpClient.getPvpRanks(ids, "en")

        then: "Retrieved list matches expected"
        ranks == parseRanks()
    }

    def "Should get pvp season ids"() {
        given: "Expected season ids"
        def ids = parseResponse("/responses/pvp/season_ids.json")

        and: "External api is stubbed"
        stubPvpSeasonsIdsResponse()

        when: "Requesting season ids"
        def seasonIds = pvpClient.getPvpSeasonIds()

        then: "Retrieved list matches expected"
        seasonIds == ids
    }

    def "Should get pvp seasons"() {
        given: "Season ids"
        def ids = ["44B85826-B5ED-4890-8C77-82DDF9F2CF2B", "95D5B290-798A-421E-A919-1C2A75F74B72"]

        and: "External api is stubbed"
        stubPvpSeasonsResponse()

        when: "Requesting pvp seasons"
        def seasons = pvpClient.getPvpSeasons(ids, "en")

        then: "Retrieved list matches expected"
        seasons == parseSeasons()
    }

    private List<PvpSeason> parseSeasons() {
        gson.fromJson(parseResponseText("/responses/pvp/seasons.json"),
                new TypeToken<List<PvpSeason>>() {}.getType())
    }

    private List<PvpRank> parseRanks() {
        gson.fromJson(parseResponseText("/responses/pvp/ranks.json"),
                new TypeToken<List<PvpRank>>() {}.getType())
    }
}
