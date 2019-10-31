package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.clients.items.FinishersClient
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class FinishersClientSpec extends WiremockConfig {

    @Subject
    def finishersClient = new FinishersClient()

    def "Should get finisher ids"() {
        given: "Expected finisher ids"
        def ids = parseResponse("/responses/items/finisher_ids.json")

        and: "External api is stubbed"
        stubResponse("/finishers", "/responses/items/finisher_ids.json")

        when: "Requesting finisher id list"
        def finisherIds = finishersClient.getFinisherIds()

        then: "Retrieved list matches expected"
        finisherIds == ids
    }

    def "Should get finishers"() {
        given: "Finisher ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/finishers?ids=1,2&lang=en", "/responses/items/finishers.json")

        when: "Requesting finishers"
        def finishers = finishersClient.getFinishers(ids, "en")

        then: "Retrieved finishers matches expected"
        finishers.size() == 2
        verifyAll(finishers.get(0)) {
            id == 1
            unlockDetails == "<c=@reminder>Unlock this PvP rank finisher by earning rank points and increasing your PvP rank.</c>"
            unlockItems == []
            order == 18
            icon == "https://render.guildwars2.com/file/807516C20D08B908946167EADD57980163EECA4E/620101.png"
            name == "Rabbit Rank Finisher"
        }
    }
}
