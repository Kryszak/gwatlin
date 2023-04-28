package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockSpec
import spock.lang.Subject

class SpecializationClientSpec extends WiremockSpec {

    @Subject
    def specializationClient = new GWSpecializationClient()

    def "Should get specialization ids"() {
        given: "External api is stubbed"
        stubResponse("/specializations", "/responses/gamemechanics/specialization_ids.json")

        when: "Retrieving specialization ids"
        def specializationIds = specializationClient.getSpecializationIds()

        then: "Retrieved list matches expected"
        specializationIds.size() == 63
    }

    def "Should get specialization"() {
        given: "Specialization id"
        def id = 1

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/specializations/1", "/responses/gamemechanics/specialization.json", lang)

        when: "Requesting specialization"
        def specialization = specializationClient.getSpecialization(id, lang)

        then: "Retrieves specialization matches expected"
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
        stubNotFoundResponse("/specializations/100", "/responses/gamemechanics/specialization_error.json")

        when: "Requesting non existing specialization"
        specializationClient.getSpecialization(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }
}
