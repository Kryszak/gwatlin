package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import spock.lang.Subject

class TraitsClientSpec extends GameMechanicsStubs {

    @Subject
    def traitsClient = new GWTraitsClient()

    def "Should get trait ids"() {
        given: "Expected id list"
        def ids = parseResponse("/responses/gamemechanics/trait_ids.json")

        and: "External api is stubbed"
        stubTraitIdsResponse()

        when: "Requesting trait ids"
        def traitIds = traitsClient.getTraitIds()

        then: "Retrieved list matches expected"
        traitIds == ids
    }

    def "Should get traits"() {
        given: "Trait ids"
        def ids = [214, 221]

        and: "External api is stubbed"
        stubTraitsResponse()

        when: "Traits are requested"
        def traits = traitsClient.getTraits(ids, "en")

        then: "Retrieved list matches expected"
        traits == parseTraits()
    }

    private List<Trait> parseTraits() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/traits.json"),
                new TypeToken<List<Trait>>() {}.getType())
    }
}
