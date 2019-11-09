package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject
import spock.lang.Unroll

class MasteriesClientSpec extends WiremockConfig {

    @Subject
    def gameMechanicsClient = new GWMasteriesClient()

    def "Should get masteries ids"() {
        given: "External api is stubbed"
        stubResponse("/masteries", "/responses/gamemechanics/masteries_ids.json")

        when: "Retrieving list of masteries ids"
        def idsList = gameMechanicsClient.getMasteriesIds()

        then: "Retrieved list matches expected"
        idsList.size() == 17
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
        verifyAll(masteries.get(0)) {
            id == 1
            name == "Exalted Lore"
            requirement == "Journey to Auric Basin to unlock the Exalted Lore Mastery track."
            order == 2
            background == "https://render.guildwars2.com/file/4E09B60E16E6A7404B0638A00D0C6A02F7294308/1228720.png"
            region == "Maguuma"
            verifyAll(levels.get(0)) {
                name == "Exalted Markings"
                description == "Gain the knowledge to read Exalted markings. You can now decipher their words and gain access to secrets of their civilization."
                instruction == "You can now interact with Exalted artifacts found in Auric Basin and the greater Maguuma Jungle."
                icon == "https://render.guildwars2.com/file/7372DCB5085D75F672B50CB8F3577373B8F90468/1228654.png"
                pointCost == 1
                expCost == 508000
            }
        }
    }

    def "Should get all masteries"() {
        given: "External api is stubbed"
        stubResponse("/masteries?ids=all&lang=en", "/responses/gamemechanics/masteries_all.json")

        when: "Retrieving all masteries"
        def masteries = gameMechanicsClient.getAllMasteries("en")

        then: "Retrieved list matches expected"
        masteries.size() == 17
    }

    private Mastery parseMastery(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), Mastery)
    }
}
