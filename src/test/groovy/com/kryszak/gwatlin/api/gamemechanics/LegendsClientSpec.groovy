package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class LegendsClientSpec extends WiremockConfig {

    @Subject
    def legendsClient = new GWLegendsClient()

    def "Should get legend ids"() {
        given: "External api is stubbed"
        stubResponse("/legends", "/responses/gamemechanics/legend_ids.json")

        when: "Requesting legend ids"
        def legendIds = legendsClient.getLegendIds()

        then: "Retrieved list matches expected"
        legendIds.size() == 6
    }

    def "Should get legends"() {
        given: "Legend ids"
        def ids = ["Legend1", "Legend2"]

        and: "External api is stubbed"
        stubResponse("/legends?ids=Legend1,Legend2&lang=en", "/responses/gamemechanics/legends.json")

        when: "Legends are requested"
        def legends = legendsClient.getLegends(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(legends.get(0)) {
            id == "Legend1"
            swap == 28229
            heal == 27220
            elite == 27760
            utilities.size() == 3
        }
    }
}
