package com.kryszak.gwatlin.api.gamemechanics

import com.google.common.reflect.TypeToken
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.race.Race
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class RacesClientSpec extends WiremockConfig {

    @Subject
    def racesClient = new GWRacesClient()

    def "Should get race ids"() {
        given: "Expected race ids"
        def ids = parseResponse("/responses/gamemechanics/race_ids.json")

        and: "External api is stubbed"
        stubResponse("/races", "/responses/gamemechanics/race_ids.json")

        when: "Requesting race ids"
        def raceIds = racesClient.getRaceIds()

        then: "Retrieved list matches expected"
        raceIds == ids
    }

    def "Should get race"() {
        given: "Race id"
        def id = "Asura"

        and: "External api is stubbed"
        stubResponse("/races/Asura", "/responses/gamemechanics/race.json")

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
        stubNotFoundResponse("/races/nobody", "/responses/gamemechanics/race_error.json")

        when: "Retrieving non existing race"
        racesClient.getRace(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private Race parseRace() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/race.json"), new TypeToken<Race>() {}.getType())
    }
}
