package com.kryszak.gwatlin.api.pvp

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.pvp.model.rank.PvpRank
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

    private List<PvpRank> parseRanks() {
        gson.fromJson(parseResponseText("/responses/pvp/ranks.json"),
                new TypeToken<List<PvpRank>>() {}.getType())
    }
}
