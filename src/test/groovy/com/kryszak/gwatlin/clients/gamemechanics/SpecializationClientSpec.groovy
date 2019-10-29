package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import spock.lang.Subject

class SpecializationClientSpec extends GameMechanicsStubs {

    @Subject
    def specializationClient = new SpecializationClient()

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
