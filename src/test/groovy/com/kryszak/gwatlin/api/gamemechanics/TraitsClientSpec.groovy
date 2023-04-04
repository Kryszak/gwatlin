package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.gamemechanics.model.trait.TraitSlot
import com.kryszak.gwatlin.api.gamemechanics.model.trait.TraitTier
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class TraitsClientSpec extends WiremockTest {

    @Subject
    def traitsClient = new GWTraitsClient()

    def "Should get trait ids"() {
        given: "External api is stubbed"
        stubResponse("/traits", "/responses/gamemechanics/trait_ids.json")

        when: "Requesting trait ids"
        def traitIds = traitsClient.getTraitIds()

        then: "Retrieved list matches expected"
        traitIds.size() == 774
    }

    def "Should get traits"() {
        given: "Trait ids"
        def ids = [214, 221]

        and: "External api is stubbed"
        stubResponse("/traits?ids=214,221&lang=en", "/responses/gamemechanics/traits.json")

        when: "Traits are requested"
        def traits = traitsClient.getTraits(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(traits.get(0)) {
            id == 214
            tier == TraitTier.MASTER
            order == 1
            name == "Raging Storm"
            description == "Critically striking a foe grants fury. Gain ferocity while under the effects of fury."
            slot == TraitSlot.MAJOR
            specialization == 41
            icon == "https://render.guildwars2.com/file/74A414B378B54431EF183A37DA37CCFFFC0E04BD/2175040.png"
            verifyAll(facts.get(0)) {
                text == "Recharge"
                type == "Recharge"
                icon == "https://render.guildwars2.com/file/D767B963D120F077C3B163A05DC05A7317D7DB70/156651.png"
            }
        }
    }
}
