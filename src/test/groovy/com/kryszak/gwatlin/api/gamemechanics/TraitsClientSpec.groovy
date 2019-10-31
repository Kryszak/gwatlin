package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import com.kryszak.gwatlin.api.gamemechanics.model.trait.TraitSlot
import com.kryszak.gwatlin.api.gamemechanics.model.trait.TraitTier
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class TraitsClientSpec extends WiremockConfig {

    @Subject
    def traitsClient = new GWTraitsClient()

    def "Should get trait ids"() {
        given: "Expected id list"
        def ids = parseResponse("/responses/gamemechanics/trait_ids.json")

        and: "External api is stubbed"
        stubResponse("/traits", "/responses/gamemechanics/trait_ids.json")

        when: "Requesting trait ids"
        def traitIds = traitsClient.getTraitIds()

        then: "Retrieved list matches expected"
        traitIds == ids
    }

    def "Should get traits"() {
        given: "Trait ids"
        def ids = [214, 221]

        and: "External api is stubbed"
        stubResponse("/traits?ids=214,221&lang=en", "/responses/gamemechanics/traits.json")

        when: "Traits are requested"
        def traits = traitsClient.getTraits(ids, "en")

        then: "Retrieved list matches expected"
        traits == parseTraits()
        verifyAll(traits.get(0)) {
            id == 214
            tier == TraitTier.MASTER
            order == 1
            name == "Raging Storm"
            description == "Critically striking a foe grants fury. Gain ferocity while under the effects of fury."
            slot == TraitSlot.MAJOR
            facts.size() == 3
            specialization == 41
            icon == "https://render.guildwars2.com/file/74A414B378B54431EF183A37DA37CCFFFC0E04BD/2175040.png"
        }
    }

    private List<Trait> parseTraits() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/traits.json"),
                new TypeToken<List<Trait>>() {}.getType())
    }
}
