package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class PetsClientSpec extends WiremockConfig {

    @Subject
    def petsClient = new GWPetsClient()

    def "Should get pet ids list"() {
        given: "External api is stubbed"
        stubResponse("/pets", "/responses/gamemechanics/pet_ids.json")

        when: "Pet ids are requested"
        def petIds = petsClient.getPetIds()

        then: "Retrieved list matches expected"
        petIds.size() == 55
    }

    def "Should get pets"() {
        given: "Pet ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/pets?ids=1,2&lang=en", "/responses/gamemechanics/pets.json")

        when: "Pets are requested"
        def pets = petsClient.getPets(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(pets.get(0)) {
            id == 1
            name == "Juvenile Jungle Stalker"
            description == "Jungle stalkers rely on their power to take down prey. They enter battle with a mighty roar, inspiring allies as they lay into their victims with claws and teeth. They love tummy rubs. —Acht"
            icon == "https://render.guildwars2.com/file/EF1CBC60372CC60E420AD479A3504D90207A9A3F/52535.png"
            verifyAll(skills.get(0)) {
                id == 12658
            }
        }
    }

    def "Should get all pets"() {
        given: "External api is stubbed"
        stubResponse("/pets?ids=all&lang=en", "/responses/gamemechanics/pets_all.json")

        when: "All pets are requested"
        def pets = petsClient.getAllPets("en")

        then: "Retrieved list matches expected"
        pets.size() == 55
    }
}
