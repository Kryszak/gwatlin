package com.kryszak.gwatlin.clients.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.achievement.model.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.outfit.Outfit
import spock.lang.Subject

class OutfitsClientSpec extends GameMechanicsStubs {

    @Subject
    def outfitsClient = new OutfitsClient()

    def "Should get outfits ids"() {
        given: "Expected list of outfits ids"
        def ids = parseResponse("/responses/gamemechanics/outfit_ids.json")

        and: "External api is stubbed"
        stubOutfitIdsResponse()

        when: "Outfit ids are requested"
        def idsList = outfitsClient.getOutfitsIds()

        then: "Retrieved list matches expected"
        idsList == ids
    }

    def "Should get outfits"() {
        given: "Outfits ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubOutfitsResponse()

        when: "Outfits are requested"
        def outfits = outfitsClient.getOutfits(ids, "en")

        then: "Retrieved outfits match expected"
        outfits == parseOutfits("outfits.json")
    }

    def "Should throw error on non existing outfit"() {
        given: "Id of non existing outfit"
        def id = 1000

        and: "External api is stubbed"
        stubOutfitErrorResponse()

        when: "Requesting non existing outfit"
        outfitsClient.getOutfits([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should retrieve all outfits"() {
        given: "External api is stubbed"
        stubAllOutfitsResponse()

        when: "Outfits are requested"
        def outfits = outfitsClient.getAllOutfits("en")

        then: "Retrieved outfits match expected"
        outfits == parseOutfits("outfits_all.json")
    }

    private List<Outfit> parseOutfits(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), new TypeToken<List<Outfit>>() {}.getType())
    }
}
