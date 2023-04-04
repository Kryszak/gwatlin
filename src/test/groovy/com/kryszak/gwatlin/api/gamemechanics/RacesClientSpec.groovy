package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class RacesClientSpec extends WiremockTest {

    @Subject
    def racesClient = new GWRacesClient()

    def "Should get race ids"() {
        given: "External api is stubbed"
        stubResponse("/races", "/responses/gamemechanics/race_ids.json")

        when: "Requesting race ids"
        def raceIds = racesClient.getRaceIds()

        then: "Retrieved list matches expected"
        raceIds.size() == 5
    }

    def "Should get race"() {
        given: "Race id"
        def id = "Asura"

        and: "External api is stubbed"
        stubResponse("/races/Asura", "/responses/gamemechanics/race.json")

        when: "Retrieving race"
        def race = racesClient.getRace(id)

        then: "Retrieved race matches expected"
        verifyAll(race) {
            id == "Asura"
            name == "Asura"
            skills.size() == 7
        }
    }

    def "Should throw exception on non existing race"() {
        given: "Non existing race id"
        def id = "nobody"

        and: "External api is stubbed"
        stubNotFoundResponse("/races/nobody", "/responses/gamemechanics/race_error.json")

        when: "Retrieving non existing race"
        racesClient.getRace(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }
}
