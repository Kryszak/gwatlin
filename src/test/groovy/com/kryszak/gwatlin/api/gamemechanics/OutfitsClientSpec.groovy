package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class OutfitsClientSpec extends WiremockConfig {

    @Subject
    def outfitsClient = new GWOutfitsClient()

    def "Should get outfits ids"() {
        given: "External api is stubbed"
        stubResponse("/outfits", "/responses/gamemechanics/outfit_ids.json")

        when: "Outfit ids are requested"
        def idsList = outfitsClient.getOutfitsIds()

        then: "Retrieved list matches expected"
        idsList.size() == 91
    }

    def "Should get outfits"() {
        given: "Outfits ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/outfits?ids=1,2&lang=en", "/responses/gamemechanics/outfits.json")

        when: "Outfits are requested"
        def outfits = outfitsClient.getOutfits(ids, "en")

        then: "Retrieved outfits match expected"
        verifyAll(outfits.get(0)) {
            id == 1
            name == "Cook's Outfit"
            icon == "https://render.guildwars2.com/file/1509D1B76FCECC111E28D2F50EBEAD5DA102995A/340522.png"
            unlockItems.size() == 1
        }
    }

    def "Should throw error on non existing outfit"() {
        given: "Id of non existing outfit"
        def id = 1000

        and: "External api is stubbed"
        stubNotFoundResponse("/outfits?ids=1000&lang=en", "/responses/gamemechanics/outfit_error.json")

        when: "Requesting non existing outfit"
        outfitsClient.getOutfits([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should retrieve all outfits"() {
        given: "External api is stubbed"
        stubResponse("/outfits?ids=all&lang=en", "/responses/gamemechanics/outfits_all.json")

        when: "Outfits are requested"
        def outfits = outfitsClient.getAllOutfits("en")

        then: "Retrieved outfits match expected"
        outfits.size() == 91
    }
}
