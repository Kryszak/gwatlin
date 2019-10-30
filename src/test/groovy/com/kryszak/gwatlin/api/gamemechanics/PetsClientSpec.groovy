package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.pet.Pet
import spock.lang.Subject

class PetsClientSpec extends GameMechanicsStubs {

    @Subject
    def petsClient = new GWPetsClient()

    def "Should get pet ids list"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/gamemechanics/pet_ids.json")

        and: "External api is stubbed"
        stubPetIdsResponse()

        when: "Pet ids are requested"
        def petIds = petsClient.getPetIds()

        then: "Retrieved list matches expected"
        petIds == ids
    }

    def "Should get pets"() {
        given: "Pet ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubPetsResponse()

        when: "Pets are requested"
        def pets = petsClient.getPets(ids, "en")

        then: "Retrieved list matches expected"
        pets == parsePets("pets.json")
    }

    def "Should get all pets"() {
        given: "External api is stubbed"
        stubAllPetsResponse()

        when: "All pets are requested"
        def pets = petsClient.getAllPets("en")

        then: "Retrieved list matches expected"
        pets == parsePets("pets_all.json")
    }

    private List<Pet> parsePets(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"), new TypeToken<List<Pet>>() {}.getType())
    }
}
