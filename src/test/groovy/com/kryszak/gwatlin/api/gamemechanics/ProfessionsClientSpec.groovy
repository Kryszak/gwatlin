package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import spock.lang.Subject

class ProfessionsClientSpec extends GameMechanicsStubs {

    @Subject
    def professionsClient = new GWProfessionsClient()

    def "Should get profession ids"() {
        given: "Expected profession ids"
        def ids = parseResponse("/responses/gamemechanics/profession_ids.json")

        and: "External api is stubbed"
        stubProfessionIdsResponse()

        when: "Requesting profession ids"
        def professionIds = professionsClient.getProfessionIds()

        then: "Retrieved list matches expected"
        professionIds == ids
    }

    def "Should get professions"() {
        given: "Profession ids"
        def ids = ["Engineer", "Warrior"]

        and: "External api is stubbed"
        stubProfessionsResponse()

        parseProfessions("professions.json")

        when: "Requesting professions"
        def professions = professionsClient.getProfessions(ids, "en")

        then: "Retrieved list matches expected"
        professions == parseProfessions("professions.json")
        verifyAll(professions.get(0)) {
            id == "Engineer"
            name == "Engineer"
            icon == "https://render.guildwars2.com/file/5CCB361F44CCC7256132405D31E3A24DACCF440A/156632.png"
            iconBig == "https://render.guildwars2.com/file/A94D00911BD47CDE39A104F90C7D07DE623554ED/156631.png"
            specializations.size() == 7
            verifyAll(weapons.hammer) {
                specialization == 43
                flags.size() == 1
                skills.size() == 5
            }
        }
    }

    def "Should get all professions"() {
        given: "External api is stubbed"
        stubAllProfessionsResponse()

        when: "Requesting professions"
        def professions = professionsClient.getAllProfessions("en")

        then: "Retrieved list matches expected"
        professions == parseProfessions("professions_all.json")
    }

    def "Should throw exception on non existing profession"() {
        given: "Id of non existing profession"
        def id = "asdf"

        and: "External api is stubbed"
        stubProfessionErrorResponse()

        when: "Requesting non existing profession"
        professionsClient.getProfessions([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private List<Profession> parseProfessions(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), new TypeToken<List<Profession>>() {}.getType())
    }
}
