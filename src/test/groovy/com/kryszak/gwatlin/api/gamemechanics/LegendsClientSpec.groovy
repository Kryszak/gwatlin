package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.legend.Legend
import spock.lang.Subject

class LegendsClientSpec extends GameMechanicsStubs {

    @Subject
    def legendsClient = new GWLegendsClient()

    def "Should get legend ids"() {
        given: "Expected list of ids"
        def ids = parseResponse("/responses/gamemechanics/legend_ids.json")

        and: "External api is stubbed"
        stubLegendIdsResponse()

        when: "Requesting legend ids"
        def legendIds = legendsClient.getLegendIds()

        then: "Retrieved list matches expected"
        legendIds == ids
    }

    def "Should get legends"() {
        given: "Legend ids"
        def ids = ["Legend1", "Legend2"]

        and: "External api is stubbed"
        stubLegendsResponse()

        when: "Legends are requested"
        def legends = legendsClient.getLegends(ids, "en")

        then: "Retrieved list matches expected"
        legends == parseLegends()
        verifyAll(legends.get(0)) {
            id == "Legend1"
            swap == 28229
            heal == 27220
            elite == 27760
            utilities.size() == 3
        }
    }

    private List<Legend> parseLegends() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/legends.json"),
                new TypeToken<List<Legend>>() {}.getType())
    }
}
