package com.kryszak.gwatlin.api.gamemechanics

import com.google.common.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.race.Race
import spock.lang.Subject

class RacesClientSpec extends GameMechanicsStubs {

    @Subject
    def racesClient = new GWRacesClient()

    def "Should get race ids"() {
        given: "Expected race ids"
        def ids = parseResponse("/responses/gamemechanics/race_ids.json")

        and: "External api is stubbed"
        stubRaceIdsResponse()

        when: "Requesting race ids"
        def raceIds = racesClient.getRaceIds()

        then: "Retrieved list matches expected"
        raceIds == ids
    }

    def "Should get race"() {
        given: "Race id"
        def id = "Asura"

        and: "External api is stubbed"
        stubRaceResponse()

        when: "Retrieving race"
        def race = racesClient.getRace(id)

        then: "Retrieved race matches expected"
        race == parseRace()
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
        stubRaceErrorResponse()

        when: "Retrieving non existing race"
        racesClient.getRace(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private Race parseRace() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/race.json"), new TypeToken<Race>() {}.getType())
    }
}
