package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import spock.lang.Subject

class SpecializationClientSpec extends GameMechanicsStubs {

    @Subject
    def specializationClient = new GWSpecializationClient()

    def "Should get specialization ids"() {
        given: "Expected specialization ids"
        def ids = parseResponse("/responses/gamemechanics/specialization_ids.json")

        and: "External api is stubbed"
        stubSpecializationIdsResponse()

        when: "Retrieving specialization ids"
        def specializationIds = specializationClient.getSpecializationIds()

        then: "Retrieved list matches expected"
        specializationIds == ids
    }

    def "Should get specialization"() {
        given: "Specialization id"
        def id = 1

        and: "External api is stubbed"
        stubSpecializationResponse()

        when: "Requesting specialization"
        def specialization = specializationClient.getSpecialization(id, "en")

        then: "Retrieves specialization matches expected"
        specialization == parseSpecialization()
        verifyAll(specialization) {
            id == 1
            name == "Dueling"
            profession == "Mesmer"
            !elite
            minorTraits.size() == 3
            majorTraits.size() == 9
            icon == "https://render.guildwars2.com/file/43C5400906A104C60F30DFE0A145D1E767353573/1012003.png"
            background == "https://render.guildwars2.com/file/992D53319C5FCD4AE841C592DC2AE91D5906AECF/1012057.png"
        }
    }

    def "Should throw exception on non existing specialization"() {
        given: "Non existing specialization id"
        def id = 100

        and: "External api is stubbed"
        stubSpecializationErrorResponse()

        when: "Requesting non existing specialization"
        specializationClient.getSpecialization(id, "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private Specialization parseSpecialization() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/specialization.json"), Specialization)
    }
}
