package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockSpec
import spock.lang.Subject

class FinishersClientSpec extends WiremockSpec {

    @Subject
    def finishersClient = new GWFinishersClient()

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

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/finishers?ids=1,2", "/responses/items/finishers.json", lang)

        when: "Requesting finishers"
        def finishers = finishersClient.getFinishers(ids, lang)

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
