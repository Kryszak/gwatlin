package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject
import spock.lang.Unroll

class MasteriesClientSpec extends WiremockConfig {

    @Subject
    def gameMechanicsClient = new GWMasteriesClient()

    def "Should get masteries ids"() {
        given: "Expected list of masteries ids"
        def ids = parseResponse("/responses/gamemechanics/masteries_ids.json")

        and: "External api is stubbed"
        stubResponse("/masteries", "/responses/gamemechanics/masteries_ids.json")

        when: "Retrieving list of masteries ids"
        def idsList = gameMechanicsClient.getMasteriesIds()

        then: "Retrieved list matches expected"
        idsList == ids
    }

    @Unroll
    def "Should get mastery in #lang"() {
        given: "Mastery id"
        def id = 1

        and: "External api is stubbed"
        stub

        when: "Retrieving mastery"
        def mastery = gameMechanicsClient.getMastery(id, language)

        then: "Retrieved mastery matches expected"
        mastery == parseMastery(file)

        where:
        lang               | language | file              | stub
        "default language" | "en"     | "mastery.json"    | stubResponse("/masteries/1?lang=en", "/responses/gamemechanics/mastery.json")
        "French"           | "fr"     | "mastery_fr.json" | stubResponse("/masteries/1?lang=fr", "/responses/gamemechanics/mastery_fr.json")
    }

    def "Should throw exception on non existing mastery"() {
        given: "Id of non existing mastery"
        def id = 40

        and: "External api is stubbed"
        stubNotFoundResponse("/masteries/40?lang=en", "/responses/gamemechanics/mastery_error.json")

        when: "Retrieving mastery"
        gameMechanicsClient.getMastery(id, "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get list of masteries"() {
        given: "Masteries ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/masteries?ids=1,2&lang=en", "/responses/gamemechanics/masteries.json")

        when: "Retrieving masteries"
        def masteries = gameMechanicsClient.getMasteries(ids, "en")

        then: "Retrieved masteries match expected"
        masteries == parseMasteries("masteries.json")
        verifyAll(masteries.get(0)) {
            id == 1
            name == "Exalted Lore"
            requirement == "Journey to Auric Basin to unlock the Exalted Lore Mastery track."
            order == 2
            background == "https://render.guildwars2.com/file/4E09B60E16E6A7404B0638A00D0C6A02F7294308/1228720.png"
            region == "Maguuma"
            levels.size() == 5
        }
    }

    def "Should get all masteries"() {
        given: "External api is stubbed"
        stubResponse("/masteries?ids=all&lang=en", "/responses/gamemechanics/masteries_all.json")

        when: "Retrieving all masteries"
        def masteries = gameMechanicsClient.getAllMasteries("en")

        then: "Retrieved list matches expected"
        masteries == parseMasteries("masteries_all.json")
    }

    private List<Mastery> parseMasteries(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"),
                new TypeToken<List<Mastery>>() {}.getType())
    }

    private Mastery parseMastery(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), Mastery)
    }
}
