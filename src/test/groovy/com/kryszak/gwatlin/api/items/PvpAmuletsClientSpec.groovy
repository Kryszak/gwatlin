package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class PvpAmuletsClientSpec extends WiremockTest {

    @Subject
    def pvpAmuletClient = new GWPvpAmuletsClient()

    def "Should get pvp amulet ids"() {
        given: "Expected list of pvp amulet ids"
        def ids = parseResponse("/responses/items/pvp_amulet_ids.json")

        and: "External api is stubbed"
        stubResponse("/pvp/amulets", "/responses/items/pvp_amulet_ids.json")

        when: "Requesting pvp amulet ids"
        def amuletIds = pvpAmuletClient.getPvpAmuletIds()

        then: "Retrieved list matches expected"
        amuletIds == ids
    }

    def "Should get pvp amulet"() {
        given: "Pvp amulet id"
        def ids = [7]

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/pvp/amulets?ids=7", "/responses/items/pvp_amulet.json", lang)

        when: "Requesting pvp amulet"
        def pvpAmulets = pvpAmuletClient.getPvpAmulets(ids, lang)

        then: "Retrieved amulet matches expected"
        verifyAll(pvpAmulets.get(0)) {
            id == 7
            name == "Barbarian Amulet"
            icon == "https://render.guildwars2.com/file/C1B81CDC163B21EE5824AD34B4E2640C5E340ACA/643283.png"
            verifyAll(attributes) {
                vitality == 1200
                power == 900
                precision == 900
            }
        }
    }
}
