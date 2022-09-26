package com.kryszak.gwatlin.api.characters

import com.kryszak.gwatlin.api.characters.model.Character
import com.kryszak.gwatlin.api.characters.model.character.Build
import com.kryszak.gwatlin.api.characters.model.character.BuildTab
import com.kryszak.gwatlin.api.characters.model.character.CharacterCore
import com.kryszak.gwatlin.api.characters.model.character.Crafting
import com.kryszak.gwatlin.api.characters.model.character.ItemStats
import com.kryszak.gwatlin.api.characters.model.character.Skills
import com.kryszak.gwatlin.api.characters.model.character.Specialization
import com.kryszak.gwatlin.api.characters.model.character.Training
import com.kryszak.gwatlin.api.characters.model.character.WvwAbility
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItemLocation
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentPvp
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
import com.kryszak.gwatlin.api.characters.model.character.inventory.Bag
import com.kryszak.gwatlin.api.characters.model.character.inventory.InventorySlot
import com.kryszak.gwatlin.api.characters.model.character.sab.CharacterSAB
import com.kryszak.gwatlin.api.characters.model.character.sab.SabSong
import com.kryszak.gwatlin.api.characters.model.character.sab.SabUnlock
import com.kryszak.gwatlin.api.characters.model.character.sab.SabZone
import com.kryszak.gwatlin.api.characters.model.character.sab.SabZoneMode
import com.kryszak.gwatlin.api.shared.ItemBinding
import com.kryszak.gwatlin.api.shared.ItemSlot
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject


class CharactersClientSpec extends WiremockConfig {

    final TARGET_SCHEMA_VERSION = "2021-07-15T13:00:00.000Z"
    final API_KEY = "1234"
    
    @Subject
    def charactersClient = new GWCharactersClient(API_KEY)

    def "Should get character"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: ""

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName",
                "/responses/characters/character.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

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
            guild == "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
            age == 4732983
            created == "2013-08-09T12:22:00Z"
            lastModified == "2022-09-24T14:27:00Z"
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
            wvwAbilities[0] == new WvwAbility(wvwAbilities[0].id, wvwAbilities[0].rank)

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
            !equipmentTabs[0].active
            equipmentTabs[1].active
            equipmentTabs[1].name == "Weaver SW DA"
            equipmentTabs[1].equipment.size() == 17
            equipmentTabs[1].equipment[1].id == 80190
            equipmentTabs[1].equipment[1].infusions.size() == 1
            equipmentTabs[1].equipment[1].dyes.size() == 4
            equipmentTabs[1].equipment[1].charges == null
            equipmentTabs[1].equipment[1].stats.id == 161
            equipmentTabs[1].equipment[1].stats.attributes == [
                    "Power": 141,
                    "Precision": 101,
                    "CritDamage": 101
            ]
            equipmentTabs[1].equipment[1].stats == new ItemStats(
                    equipmentTabs[1].equipment[1].stats.id,
                    equipmentTabs[1].equipment[1].stats.attributes
            )

            training.size() == 13
            training[5].id == 34
            training[5].spent == 60
            training[5].done
            training[12].id == 467
            training[12].spent == 250
            training[12].done

            training[12] == new Training(training[12].id, training[12].spent, training[12].done)

            it == new Character(
                    name,
                    race,
                    gender,
                    profession,
                    level,
                    guild,
                    age,
                    created,
                    lastModified,
                    deaths,
                    title,
                    flags,
                    crafting,
                    backstory,
                    wvwAbilities,
                    training,
                    buildTabsUnlocked,
                    activeBuildTab,
                    buildTabs,
                    equipmentTabsUnlocked,
                    activeEquipmentTab,
                    equipment,
                    equipmentTabs
            )
        }
    }

    def "Should get character buildtabs"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/buildtabs?tabs=all",
                "/responses/characters/buildtabs.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getBuildTabs(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 4
        }
    }

    def "Should get character buildtab"() {
        given: "Character name"
        def characterName = "Test Character"
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
        stubAuthResponseWithSchema(
                "/characters/$escapedName/buildtabs?tab=$buildtabNumber",
                "/responses/characters/buildtab.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getBuildTab(characterName, buildtabNumber)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 2
            active
            build.name == "Power Weaver"
            build.profession == "Elementalist"
            build.specializations[0].id == 41
            build.specializations[0].traits == [232, 214, 226] as Set
            build.skills.heal == 5503
            build.skills.utilities == [5734, 5539, 40183] as Set
            build.skills.elite == 5516
            build.skills.legends == null
            build.aquaticSkills.heal == 5569
            build.aquaticSkills.utilities == [5554, 5535, 5536] as Set
            build.aquaticSkills.elite == 5534
            build.aquaticSkills.legends == null
            build == referenceBuild

            it == new BuildTab(tab, active, build)
        }
    }

    def "Should get character active build tab"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/buildtabs/active",
                "/responses/characters/buildtab.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getActiveBuildTab(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 2
        }
    }

    def "Should get character backstory"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/backstory",
                "/responses/characters/backstory.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getBackstory(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            it == ["15-84", "7-55", "186-161", "16-88", "17-93"]
        }
    }

    def "Should get character core"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/core",
                "/responses/characters/character-core.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getCore(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            name == characterName
            race == "Charr"
            gender == "Male"
            profession == "Elementalist"
            level == 80
            guild == "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
            age == 4732983
            created == "2013-08-09T12:22:00Z"
            lastModified == "2022-09-24T14:27:00Z"
            deaths == 3519
            title == 300

            it == new CharacterCore(
                    name, race, gender, profession, level, guild, age, created, lastModified, deaths, title
            )
        }
    }

    def "Should get character crafting"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/crafting",
                "/responses/characters/crafting.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getCrafting(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 2
            it[0].discipline == "Jeweler"
            it[0].rating == 400
            it[0].active
            it[0] == new Crafting(it[0].discipline, it[0].rating, it[0].active)
        }
    }

    def "Should get character equipment"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/equipment",
                "/responses/characters/equipment.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getEquipment(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 76

            it[4].id == 80111
            it[4].binding == ItemBinding.CHARACTER
            it[4].location == EquipmentItemLocation.EQUIPPED
            it[4].boundTo == characterName

            it[6].upgrades == [91595]

            it[6] == new EquipmentItem(
                    it[6].id,
                    it[6].slot,
                    it[6].binding,
                    it[6].boundTo,
                    it[6].infusions,
                    it[6].location,
                    it[6].skin,
                    it[6].upgrades,
                    it[6].stats,
                    it[6].dyes,
                    it[6].charges,
                    it[6].tabs
            )
        }
    }

    def "Should get character equipment tabs"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/equipmenttabs?tabs=all",
                "/responses/characters/equipmenttabs.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getEquipmentTabs(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 5
        }
    }

    def "Should get character equipment tab"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "Equipmenttab number"
        def equipmenttabNumber = 2

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/equipmenttabs?tab=$equipmenttabNumber",
                "/responses/characters/equipmenttab.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getEquipmentTab(characterName, equipmenttabNumber)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 3
            name == "Quickness Catalyst"
            active
            equipment.size() == 16

            equipmentPvp.amulet == 8
            equipmentPvp.rune == 21215
            equipmentPvp.sigils == [21155, null, 21152, null]

            equipmentPvp == new EquipmentPvp(equipmentPvp.amulet, equipmentPvp.rune, equipmentPvp.sigils)

            it == new EquipmentTab(tab, name, active, equipment, equipmentPvp)
        }
    }

    def "Should get character active equipment tab"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/equipmenttabs/active",
                "/responses/characters/equipmenttab.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getActiveEquipmentTab(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            tab == 3
        }
    }

    def "Should get character inventory"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/inventory",
                "/responses/characters/inventory.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getInventory(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            size() == 6

            it[0].inventory[6].id == 2258
            it[0].inventory[6].upgrades == [24774]
            it[0].inventory[6].upgradeSlotIndices == [0]
            it[0].inventory[6].dyes == null
            it[0].inventory[6].boundTo == null
            it[0].inventory[6].stats == null
            it[0].inventory[6].charges == null
            it[0].inventory[6].infusions == null

            it[0].inventory[9].skin == 746
            it[0].inventory[9].upgrades == [71425]
            it[0].inventory[9].dyes == [6, 315, 453, 1]
            it[0].inventory[9].binding == ItemBinding.CHARACTER
            it[0].inventory[9].boundTo == characterName


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

            it[4] == new Bag(it[4].id, it[4].size, it[4].inventory)

            it[4].inventory[0] == new InventorySlot(
                    it[4].inventory[0].id,
                    it[4].inventory[0].count,
                    it[4].inventory[0].charges,
                    it[4].inventory[0].infusions,
                    it[4].inventory[0].upgrades,
                    it[4].inventory[0].upgradeSlotIndices,
                    it[4].inventory[0].skin,
                    it[4].inventory[0].stats,
                    it[4].inventory[0].dyes,
                    it[4].inventory[0].binding,
                    it[4].inventory[0].boundTo
            )
        }
    }

    def "Should get character recipes"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/recipes",
                "/responses/characters/recipes.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

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

    def "Should get character training"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/training",
                "/responses/characters/training.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

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

    def "Should get character heropoints"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/heropoints",
                "/responses/characters/heropoints.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

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

    def "Should get character quests"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/quests",
                "/responses/characters/quests.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

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

    def "Should get character SAB details"() {
        given: "Character name"
        def characterName = "Test Character"
        def escapedName = characterName.replace(" ", "%20")

        and: "External api is stubbed"
        stubAuthResponseWithSchema(
                "/characters/$escapedName/sab",
                "/responses/characters/sab.json",
                API_KEY,
                TARGET_SCHEMA_VERSION
        )

        when: "Requesting character"
        def character = charactersClient.getSAB(characterName)

        then: "Retrieved details matches expected"
        verifyAll(character) {
            zones.size() == 11
            zones[4].id == 5
            zones[4].mode == SabZoneMode.NORMAL
            zones[4].world == 2
            zones[4].zone == 2
            zones[4] == new SabZone(
                    zones[4].id,
                    zones[4].mode,
                    zones[4].world,
                    zones[4].zone
            )

            zones[5].id == 13
            zones[5].mode == SabZoneMode.INFANTILE
            zones[5].world == 1
            zones[5].zone == 1

            unlocks.size() == 8
            unlocks[0].id == 6
            unlocks[0].name == "whip"
            unlocks[0] == new SabUnlock(unlocks[0].id, unlocks[0].name)

            // Apparently there's an API bug, where the unlock with ID 10 does not have a name attribute
            // https://wiki.guildwars2.com/wiki/API:2/characters/:id/sab - bottom of the page
            unlocks[1] == new SabUnlock(10, null)

            unlocks[4].id == 18

            songs.size() == 1
            songs[0].id == 1
            songs[0].name == "secret_song"
            songs[0] == new SabSong(songs[0].id, songs[0].name)

            it == new CharacterSAB(zones, unlocks, songs)
        }
    }
}
