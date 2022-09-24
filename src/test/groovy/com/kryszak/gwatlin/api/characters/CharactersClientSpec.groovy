package com.kryszak.gwatlin.api.characters

import com.kryszak.gwatlin.api.characters.model.character.Build
import com.kryszak.gwatlin.api.characters.model.character.Crafting
import com.kryszak.gwatlin.api.characters.model.character.Skills
import com.kryszak.gwatlin.api.characters.model.character.Specialization
import com.kryszak.gwatlin.api.characters.model.character.WvwAbility
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItemLocation
import com.kryszak.gwatlin.api.characters.model.character.sab.SabZoneMode
import com.kryszak.gwatlin.api.shared.ItemBinding
import com.kryszak.gwatlin.api.shared.ItemSlot
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

import java.time.format.DateTimeFormatter

class CharactersClientSpec extends WiremockConfig {

    def API_KEY = "1234"

    @Subject
    def charactersClient = new GWCharactersClient(API_KEY)

    def "Should get Character"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName", "/responses/characters/character.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getCharacter(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            name == characterName
            race == "Charr"
            gender == "Male"
            flags == []
            profession == "Elementalist"
            level == 80
            guild == "7311DE04-55DD-E811-81A8-E944283D67C1"
            age == 4732983
            created == "2013-08-09T12:22:00Z"
            deaths == 3519
            crafting.size() == 2
            title == 300
            backstory.size() == 5
            backstory[0] == "15-84"
            backstory[1] == "7-55"
            backstory[2] == "186-161"
            backstory[3] == "16-88"
            backstory[4] == "17-93"
            wvwAbilities.size() == 5

            def matchWvWAbilities = { WvwAbility wvwAbility, int id, int rank ->
                return wvwAbility.id == id && wvwAbility.rank == rank
            }

            matchWvWAbilities(wvwAbilities[0], 24, 6)
            matchWvWAbilities(wvwAbilities[1], 7, 5)
            matchWvWAbilities(wvwAbilities[2], 25, 5)
            matchWvWAbilities(wvwAbilities[3], 17, 5)
            matchWvWAbilities(wvwAbilities[4], 26, 7)

            buildTabsUnlocked == 4
            activeBuildTab == 2
            buildTabs.size() == 4

            equipment.size() == 76
            equipment[2].id == 80190
            equipment[2].slot == ItemSlot.COAT
            equipment[2].skin == 1419
            equipment[2].binding == ItemBinding.ACCOUNT
            equipment[2].location == EquipmentItemLocation.EQUIPPED_FROM_LEGENDARY_ARMORY
            equipment[2].tabs.first() == 2

            equipmentTabsUnlocked == 5
            activeEquipmentTab == 2
            equipmentTabs.size() == 5
            !equipmentTabs[0].isActive()
            equipmentTabs[1].isActive()
            equipmentTabs[1].name == "Weaver SW DA"
            equipmentTabs[1].equipment.size() == 17
            equipmentTabs[1].equipment[1].id == 80190
            equipmentTabs[1].equipment[1].infusions.size() == 1
            equipmentTabs[1].equipment[1].dyes.size() == 4


            training.size() == 13
            training[5].id == 34
            training[5].spent == 60
            training[5].done
            training[12].id == 467
            training[12].spent == 250
            training[12].done



        }
    }

    def "Should get Character buildtabs"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/buildtabs?tabs=all", "/responses/characters/buildtabs.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getBuildTabs(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 4
        }
    }

    def "Should get Character buildtab"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "Buildtab number"
        def buildtabNumber = 2

        and: "Build object"
        def referenceBuild = new Build(
                "Power Weaver",
                "Elementalist",
                [
                        new Specialization(41, [232, 214, 226] as Set),
                        new Specialization(31, [296, 334, 1510] as Set),
                        new Specialization(56, [2177, 2061, 2131] as Set),
                ],
                new Skills(5503, [5734, 5539, 40183] as Set, 5516),
                new Skills(5569, [5554, 5535, 5536] as Set, 5534)
        )

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/buildtabs?tab=$buildtabNumber", "/responses/characters/buildtab.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getBuildTab(characterName, buildtabNumber)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 2
            isActive()
            build == referenceBuild
        }
    }

    def "Should get Character active build tab"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/buildtabs/active", "/responses/characters/buildtab.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getActiveBuildTab(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 2
        }
    }

    def "Should get Character backstory"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/backstory", "/responses/characters/backstory.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getBackstory(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            it == ["15-84", "7-55", "186-161", "16-88", "17-93"]
        }
    }

    def "Should get Character core"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/core", "/responses/characters/character-core.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getCore(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            name == characterName
            race == "Charr"
            gender == "Male"
            profession == "Elementalist"
            level == 80
            guild == "7311DE04-55DD-E811-81A8-E944283D67C1"
            age == 4732983
            created == "2013-08-09T12:22:00Z"
            lastModified == "2022-09-24T14:27:00Z"
            deaths == 3519
            title == 300
        }
    }

    def "Should get Character crafting"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/crafting", "/responses/characters/crafting.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getCrafting(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 2
            it == [
                new Crafting("Jeweler", 400, true),
                new Crafting("Tailor", 500, true),
            ]
        }
    }

    def "Should get Character equipment"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/equipment", "/responses/characters/equipment.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getEquipment(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 76
        }
    }

    def "Should get Character equipment tabs"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/equipmenttabs?tabs=all", "/responses/characters/equipmenttabs.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getEquipmentTabs(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 5
        }
    }

    def "Should get Character equipment tab"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "Equipmenttab number"
        def equipmenttabNumber = 2

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/equipmenttabs?tab=$equipmenttabNumber", "/responses/characters/equipmenttab.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getEquipmentTab(characterName, equipmenttabNumber)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 3
            name == "Quickness Catalyst"
            isActive()
            equipment.size() == 16
        }
    }

    def "Should get Character active equipment tab"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/equipmenttabs/active", "/responses/characters/equipmenttab.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getActiveEquipmentTab(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 3
        }
    }

    def "Should get Character inventory"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/inventory", "/responses/characters/inventory.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getInventory(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 6

            it[1].id == 45053
            it[1].size == 18
            it[1].inventory.size() == 18

            it[1].inventory[9].binding == null

            it[1].inventory[10].id == 79835
            it[1].inventory[10].count == 10
            it[1].inventory[10].binding == ItemBinding.CHARACTER
            it[1].inventory[10].boundTo == characterName

            it[4].id == 9423
            it[4].size == 15
            it[4].inventory.size() == 15
            it[4].inventory[3] == null
            it[4].inventory[0].id == 23001
            it[4].inventory[0].count == 1
            it[4].inventory[0].binding == ItemBinding.ACCOUNT
        }
    }

    def "Should get Character recipes"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/recipes", "/responses/characters/recipes.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getRecipes(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 776
            containsAll([
                    3,
                    3415,
                    13839
            ])
        }
    }

    def "Should get Character training"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/training", "/responses/characters/training.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getTraining(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 13
            it[5].id == 34
            it[5].spent == 60
            it[5].done
        }
    }

    def "Should get Character heropoints"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/heropoints", "/responses/characters/heropoints.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getHeropoints(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 165
            containsAll([
                    "0-0",
                    "0-67",
                    "0-113",
                    "0-232"
            ])
        }
    }

    def "Should get Character quests"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/quests", "/responses/characters/quests.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getQuests(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 108
            containsAll([
                    71,
                    254,
                    488,
                    608
            ])
        }
    }

    def "Should get Character SAB details"() {
        given: "Character name"
        def characterName = "Cados Frazar"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponse("/characters/$escapedName/sab", "/responses/characters/sab.json", API_KEY)

        when: "Requesting character"
        def character = charactersClient.getSAB(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            zones.size() == 11
            zones[4].id == 5
            zones[4].mode == SabZoneMode.NORMAL
            zones[4].world == 2
            zones[4].zone == 2

            zones[5].id == 13
            zones[5].mode == SabZoneMode.INFANTILE
            zones[5].world == 1
            zones[5].zone == 1

            unlocks.size() == 7
            unlocks[0].id == 6
            unlocks[3].id == 18
        }
    }

}
