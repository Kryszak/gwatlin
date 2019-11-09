package com.kryszak.gwatlin.api.wvw

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class WvwClientSpec extends WiremockConfig {

    @Subject
    def wvwClient = new GWWvwClient()

    def "Should get ability ids"() {
        given: "External api is stubbed"
        stubResponse("/wvw/abilities", "/responses/wvw/ability_ids.json")

        when: "Requesting ability ids"
        def ids = wvwClient.getAbilityIds()

        then: "Retrieved ids match expected"
        ids.size() == 22
    }

    def "Should get ability"() {
        given: "External api is stubbed"
        stubResponse("/wvw/abilities?ids=2&lang=en", "/responses/wvw/ability.json")

        when: "Requesting ability"
        def abilities = wvwClient.getAbilities([2], "en")

        then: "Retrieved ability matches expected"
        verifyAll(abilities.get(0)) {
            id == 2
            name == "Guard Killer"
            description == "Increases damage to enemy guards"
            icon == "https://render.guildwars2.com/file/C5E6E906927E2C6311036C11F1306CCF57CBF259/544537.png"
            verifyAll(ranks.get(0)) {
                cost == 1
                effect == "4% damage to guards"
            }
        }
    }
}
