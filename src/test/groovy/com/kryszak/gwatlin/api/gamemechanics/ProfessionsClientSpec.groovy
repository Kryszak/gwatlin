package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import com.kryszak.gwatlin.api.gamemechanics.model.profession.TrainingCategory
import com.kryszak.gwatlin.api.gamemechanics.model.profession.TrainingTrackType
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class ProfessionsClientSpec extends WiremockConfig {

    @Subject
    def professionsClient = new GWProfessionsClient()

    def "Should get profession ids"() {
        given: "External api is stubbed"
        stubResponse("/professions", "/responses/gamemechanics/profession_ids.json")

        when: "Requesting profession ids"
        def professionIds = professionsClient.getProfessionIds()

        then: "Retrieved list matches expected"
        professionIds.size() == 9
    }

    def "Should get professions"() {
        given: "Profession ids"
        def ids = ["Engineer", "Warrior"]

        and: "External api is stubbed"
        stubResponse("/professions?ids=Engineer,Warrior&lang=en", "/responses/gamemechanics/professions.json")

        when: "Requesting professions"
        def professions = professionsClient.getProfessions(ids, "en")

        then: "Retrieved list matches expected"
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
            flags.size() == 1
            verifyAll(training.get(0)) {
                id == 21
                category == TrainingCategory.SKILLS
                name == "Elixir Training"
                verifyAll(track.get(0)) {
                    cost == 2
                    type == TrainingTrackType.SKILL
                    skillId == 5821
                }
            }
        }
    }

    def "Should get all professions"() {
        given: "External api is stubbed"
        stubResponse("/professions?ids=all&lang=en", "/responses/gamemechanics/professions_all.json")

        when: "Requesting professions"
        def professions = professionsClient.getAllProfessions("en")

        then: "Retrieved list matches expected"
        professions.size() == 9
    }

    def "Should throw exception on non existing profession"() {
        given: "Id of non existing profession"
        def id = "asdf"

        and: "External api is stubbed"
        stubNotFoundResponse("/professions?ids=asdf&lang=en", "/responses/gamemechanics/professions_error.json")

        when: "Requesting non existing profession"
        professionsClient.getProfessions([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private List<Profession> parseProfessions(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), new TypeToken<List<Profession>>() {
        }.getType())
    }
}
