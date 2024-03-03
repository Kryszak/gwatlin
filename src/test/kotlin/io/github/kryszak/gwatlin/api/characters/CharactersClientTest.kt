package io.github.kryszak.gwatlin.api.characters

import io.github.kryszak.gwatlin.api.characters.model.character.WvwAbility
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItemLocation
import io.github.kryszak.gwatlin.api.characters.model.character.sab.SabZoneMode
import io.github.kryszak.gwatlin.api.shared.ItemBinding
import io.github.kryszak.gwatlin.api.shared.ItemSlot
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class CharactersClientTest : BaseWiremockTest() {

    private val targetSchemaVersion = "2021-07-15T13:00:00.000Z"
    private val apiKey = "1234"

    private val charactersClient = GWCharactersClient(apiKey)

    init {
        should("Get list of characters") {
            // given
            stubResponse(
                "/characters",
                "/responses/characters/characters.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val characters = charactersClient.getCharacters()

            // then
            characters shouldHaveSize 2
            characters shouldContainAll listOf("Character-1", "Character-2")
        }

        should("Get character") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName",
                "/responses/characters/character.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val character = charactersClient.getCharacter(characterName)

            // then
            assertSoftly(character) {
                name shouldBe characterName
                race shouldBe "Charr"
                gender shouldBe "Male"
                flags.shouldBeEmpty()
                profession shouldBe "Elementalist"
                level shouldBe 80
                guild shouldBe "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
                age shouldBe 4732983
                created shouldBe "2013-08-09T12:22:00Z"
                lastModified shouldBe "2022-09-24T14:27:00Z"
                deaths shouldBe 3519
                crafting shouldHaveSize 2
                title shouldBe 300
                backstory shouldContainExactly listOf("15-84", "7-55", "186-161", "16-88", "17-93")
                wvwAbilities shouldContainExactly listOf(
                    WvwAbility(24, 6),
                    WvwAbility(7, 5),
                    WvwAbility(25, 5),
                    WvwAbility(17, 5),
                    WvwAbility(26, 7)
                )
                buildTabsUnlocked shouldBe 4
                activeBuildTab shouldBe 2
                buildTabs shouldHaveSize 4
                equipment shouldHaveSize 76
                assertSoftly(equipment[2]) {
                    id shouldBe 80190
                    slot shouldBe ItemSlot.COAT
                    skin shouldBe 1419
                    binding shouldBe ItemBinding.ACCOUNT
                    location shouldBe EquipmentItemLocation.EQUIPPED_FROM_LEGENDARY_ARMORY
                    tabs.first() shouldBe 2
                }
                equipmentTabsUnlocked shouldBe 5
                activeEquipmentTab shouldBe 2
                equipmentTabs shouldHaveSize 5
                assertSoftly(equipmentTabs[0]) {
                    isActive.shouldBeFalse()
                }
                assertSoftly(equipmentTabs[1]) {
                    name shouldBe "Weaver SW DA"
                    isActive.shouldBeTrue()
                    equipment shouldHaveSize 17
                    assertSoftly(equipment[1]) {
                        id shouldBe 80190
                        infusions shouldHaveSize 1
                        dyes shouldHaveSize 4
                        charges shouldBe null
                        assertSoftly(stats!!) {
                            id shouldBe 161
                            attributes shouldBe mapOf(
                                "Power" to 141,
                                "Precision" to 101,
                                "CritDamage" to 101
                            )
                        }
                    }
                }
                training shouldHaveSize 13
                assertSoftly(training[5]) {
                    id shouldBe 34
                    spent shouldBe 60
                    done.shouldBeTrue()
                }
                assertSoftly(training[12]) {
                    id shouldBe 467
                    spent shouldBe 250
                    done.shouldBeTrue()
                }

            }
        }

        should("Get character without guild") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName",
                "/responses/characters/character-without-guild.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val character = charactersClient.getCharacter(characterName)

            // then
            assertSoftly(character) {
                name shouldBe characterName
                race shouldBe "Charr"
                gender shouldBe "Male"
                flags.shouldBeEmpty()
                profession shouldBe "Elementalist"
                level shouldBe 80
                guild.shouldBeNull()
                age shouldBe 4732983
                created shouldBe "2013-08-09T12:22:00Z"
                lastModified shouldBe "2022-09-24T14:27:00Z"
                deaths shouldBe 3519
                crafting shouldHaveSize 2
                title shouldBe 300
                backstory shouldContainExactly listOf("15-84", "7-55", "186-161", "16-88", "17-93")
                wvwAbilities shouldContainExactly listOf(
                    WvwAbility(24, 6),
                    WvwAbility(7, 5),
                    WvwAbility(25, 5),
                    WvwAbility(17, 5),
                    WvwAbility(26, 7)
                )
                buildTabsUnlocked shouldBe 4
                activeBuildTab shouldBe 2
                buildTabs shouldHaveSize 4
                equipment shouldHaveSize 76
                assertSoftly(equipment[2]) {
                    id shouldBe 80190
                    slot shouldBe ItemSlot.COAT
                    skin shouldBe 1419
                    binding shouldBe ItemBinding.ACCOUNT
                    location shouldBe EquipmentItemLocation.EQUIPPED_FROM_LEGENDARY_ARMORY
                    tabs.first() shouldBe 2
                }
                equipmentTabsUnlocked shouldBe 5
                activeEquipmentTab shouldBe 2
                equipmentTabs shouldHaveSize 5
                assertSoftly(equipmentTabs[0]) {
                    isActive.shouldBeFalse()
                }
                assertSoftly(equipmentTabs[1]) {
                    name shouldBe "Weaver SW DA"
                    isActive.shouldBeTrue()
                    equipment shouldHaveSize 17
                    assertSoftly(equipment[1]) {
                        id shouldBe 80190
                        infusions shouldHaveSize 1
                        dyes shouldHaveSize 4
                        charges shouldBe null
                        assertSoftly(stats!!) {
                            id shouldBe 161
                            attributes shouldBe mapOf(
                                "Power" to 141,
                                "Precision" to 101,
                                "CritDamage" to 101
                            )
                        }
                    }
                }
                training shouldHaveSize 13
                assertSoftly(training[5]) {
                    id shouldBe 34
                    spent shouldBe 60
                    done.shouldBeTrue()
                }
                assertSoftly(training[12]) {
                    id shouldBe 467
                    spent shouldBe 250
                    done.shouldBeTrue()
                }

            }
        }

        should("Get character with null specializations") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName",
                "/responses/characters/character-with-null-specializations.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val character = charactersClient.getCharacter(characterName)

            // then
            assertSoftly(character) {
                name shouldBe characterName
                race shouldBe "Charr"
                gender shouldBe "Male"
                flags.shouldBeEmpty()
                profession shouldBe "Elementalist"
                level shouldBe 80
                guild.shouldBeNull()
                age shouldBe 4732983
                created shouldBe "2013-08-09T12:22:00Z"
                lastModified shouldBe "2022-09-24T14:27:00Z"
                deaths shouldBe 3519
                crafting shouldHaveSize 2
                title shouldBe null
                backstory shouldContainExactly listOf("15-84", "7-55", "186-161", "16-88", "17-93")
                wvwAbilities shouldContainExactly listOf(
                    WvwAbility(24, 6),
                    WvwAbility(7, 5),
                    WvwAbility(25, 5),
                    WvwAbility(17, 5),
                    WvwAbility(26, 7)
                )
                buildTabsUnlocked shouldBe 4
                activeBuildTab shouldBe 2
                buildTabs shouldHaveSize 4
                equipment shouldHaveSize 78
                assertSoftly(equipment[2]) {
                    id shouldBe 80190
                    slot shouldBe ItemSlot.COAT
                    skin shouldBe 1419
                    binding shouldBe ItemBinding.ACCOUNT
                    location shouldBe EquipmentItemLocation.EQUIPPED_FROM_LEGENDARY_ARMORY
                    tabs.first() shouldBe 2
                }
                assertSoftly(equipment[20]) {
                    slot shouldBe ItemSlot.FISHINGROD
                }
                assertSoftly(equipment[21]) {
                    slot shouldBe ItemSlot.RELIC
                }
                equipmentTabsUnlocked shouldBe 5
                activeEquipmentTab shouldBe 2
                equipmentTabs shouldHaveSize 5
                assertSoftly(equipmentTabs[0]) {
                    isActive.shouldBeFalse()
                }
                assertSoftly(equipmentTabs[1]) {
                    name shouldBe "Weaver SW DA"
                    isActive.shouldBeTrue()
                    equipment shouldHaveSize 17
                    assertSoftly(equipment[1]) {
                        id shouldBe 80190
                        infusions shouldHaveSize 1
                        dyes shouldHaveSize 4
                        charges shouldBe null
                        assertSoftly(stats!!) {
                            id shouldBe 161
                            attributes shouldBe mapOf(
                                "Power" to 141,
                                "Precision" to 101,
                                "CritDamage" to 101
                            )
                        }
                    }
                }
                training shouldHaveSize 13
                assertSoftly(training[5]) {
                    id shouldBe 34
                    spent shouldBe 60
                    done.shouldBeTrue()
                }
                assertSoftly(training[12]) {
                    id shouldBe 467
                    spent shouldBe 250
                    done.shouldBeTrue()
                }
                assertSoftly(buildTabs[0].build.specializations[0]) {
                    id shouldBe 31
                    traits shouldContainExactly setOf(296, 334, 1510)
                }
                assertSoftly(buildTabs[0].build.specializations[1]) {
                    id shouldBe null
                    traits shouldContainExactly setOf(232, null)
                }
                assertSoftly(buildTabs[0].build.specializations[2]) {
                    id shouldBe null
                    traits shouldContainExactly setOf(null)
                }
            }
        }

        should("Get character buildtabs") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/buildtabs?tabs=all",
                "/responses/characters/buildtabs.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val buildTabs = charactersClient.getBuildTabs(characterName)

            // then
            buildTabs shouldHaveSize 4
        }

        should("Get character buildtab") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            val buildtabNumber = 2
            stubResponse(
                "/characters/$escapedName/buildtabs?tab=$buildtabNumber",
                "/responses/characters/buildtab.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val buildTab = charactersClient.getBuildTab(characterName, buildtabNumber)

            // then
            assertSoftly(buildTab) {
                tab shouldBe 2
                isActive.shouldBeTrue()
                assertSoftly(build) {
                    name shouldBe "Power Weaver"
                    profession shouldBe "Elementalist"
                    assertSoftly(specializations[0]) {
                        id shouldBe 41
                        traits shouldContainExactly setOf(232, 214, 226)
                    }
                    assertSoftly(skills) {
                        heal shouldBe 5503
                        utilities shouldContainExactly setOf(5734, 5539, 40183)
                        elite shouldBe 5516
                        legends.shouldBeNull()
                    }
                    assertSoftly(aquaticSkills) {
                        heal shouldBe 5569
                        utilities shouldContainExactly setOf(5554, 5535, 5536)
                        elite shouldBe 5534
                        legends.shouldBeNull()
                    }
                }
            }
        }

        should("Get character active build tab") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/buildtabs/active",
                "/responses/characters/buildtab.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val characterActiveBuildTab = charactersClient.getActiveBuildTab(characterName)

            // then
            characterActiveBuildTab.tab shouldBe 2
        }

        should("Get character backstory") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/backstory",
                "/responses/characters/backstory.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val backstory = charactersClient.getBackstory(characterName)

            // then
            backstory shouldBe listOf("15-84", "7-55", "186-161", "16-88", "17-93")
        }

        should("Get character core") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/core",
                "/responses/characters/character-core.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val characterCore = charactersClient.getCore(characterName)

            // then
            assertSoftly(characterCore) {
                name shouldBe characterName
                race shouldBe "Charr"
                gender shouldBe "Male"
                profession shouldBe "Elementalist"
                level shouldBe 80
                guild shouldBe "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
                age shouldBe 4732983
                created shouldBe "2013-08-09T12:22:00Z"
                lastModified shouldBe "2022-09-24T14:27:00Z"
                deaths shouldBe 3519
                title shouldBe 300
            }
        }

        should("Get character crafting") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/crafting",
                "/responses/characters/crafting.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val craftings = charactersClient.getCrafting(characterName)

            // then
            assertSoftly(craftings!!) {
                size shouldBe 2
                assertSoftly(it[0]) {
                    discipline shouldBe "Jeweler"
                    rating shouldBe 400
                    active.shouldBeTrue()
                }
            }
        }

        should("Get character equipment") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/equipment",
                "/responses/characters/equipment.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val equipmentItems = charactersClient.getEquipment(characterName)

            // then
            assertSoftly(equipmentItems!!) {
                size shouldBe 76
                assertSoftly(it[4]) {
                    id shouldBe 80111
                    binding shouldBe ItemBinding.CHARACTER
                    location shouldBe EquipmentItemLocation.EQUIPPED
                    boundTo shouldBe characterName
                }
                it[6].upgrades shouldBe listOf(91595)
            }
        }

        should("Get character equipment tabs") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/equipmenttabs?tabs=all",
                "/responses/characters/equipmenttabs.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val equipmentTabs = charactersClient.getEquipmentTabs(characterName)

            // then
            equipmentTabs shouldHaveSize 5
        }

        should("Get character equipment tab") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            val equipmenttabNumber = 2
            stubResponse(
                "/characters/$escapedName/equipmenttabs?tab=$equipmenttabNumber",
                "/responses/characters/equipmenttab.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val equipmentTab = charactersClient.getEquipmentTab(characterName, equipmenttabNumber)

            // then
            assertSoftly(equipmentTab) {
                tab shouldBe 3
                name shouldBe "Quickness Catalyst"
                isActive.shouldBeTrue()
                equipment shouldHaveSize 16
                assertSoftly(equipmentPvp) {
                    amulet shouldBe 8
                    rune shouldBe 21215
                    sigils shouldBe listOf(21155, null, 21152, null)
                }
            }
        }

        should("Get character active equipment tab") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/equipmenttabs/active",
                "/responses/characters/equipmenttab.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val equipmentTab = charactersClient.getActiveEquipmentTab(characterName)

            // then
            equipmentTab.tab shouldBe 3
        }

        should("Get character inventory") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/inventory",
                "/responses/characters/inventory.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val bag = charactersClient.getInventory(characterName)

            // then
            assertSoftly(bag!!) {
                size shouldBe 7
                assertSoftly(it[0]!!) {
                    assertSoftly(inventory[6]!!) {
                        id shouldBe 2258
                        upgrades shouldBe listOf(24774)
                        upgradeSlotIndices shouldBe listOf(0)
                        dyes.shouldBeEmpty()
                        boundTo.shouldBeNull()
                        stats.shouldBeNull()
                        charges.shouldBeNull()
                        infusions.shouldBeEmpty()
                    }
                    assertSoftly(inventory[9]!!) {
                        skin shouldBe 746
                        upgrades shouldBe listOf(71425)
                        dyes shouldBe listOf(6, 315, 453, 1)
                        binding shouldBe ItemBinding.CHARACTER
                        boundTo shouldBe characterName
                    }
                }
                assertSoftly(it[1]!!) {
                    id shouldBe 45053
                    size shouldBe 18
                    inventory shouldHaveSize 18
                    inventory[9]!!.binding.shouldBeNull()
                    assertSoftly(inventory[10]!!) {
                        id shouldBe 79835
                        count shouldBe 10
                        binding shouldBe ItemBinding.CHARACTER
                        boundTo shouldBe characterName
                    }
                }
                assertSoftly(it[4]!!) {
                    id shouldBe 9423
                    size shouldBe 15
                    inventory shouldHaveSize 15
                    inventory[3].shouldBeNull()
                    assertSoftly(inventory[0]!!) {
                        id shouldBe 23001
                        count shouldBe 1
                        binding shouldBe ItemBinding.ACCOUNT
                    }
                }
                it[6] shouldBe null
            }
        }

        should("Get character recipes") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/recipes",
                "/responses/characters/recipes.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val recipes = charactersClient.getRecipes(characterName)

            // then
            recipes.shouldNotBeNull()
            recipes shouldHaveSize 776
            recipes shouldContainAll listOf(3, 3415, 13839)
        }

        should("Get character training") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/training",
                "/responses/characters/training.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val trainings = charactersClient.getTraining(characterName)

            // then
            assertSoftly(trainings!!) {
                size shouldBe 13
                assertSoftly(it[5]) {
                    id shouldBe 34
                    spent shouldBe 60
                    done.shouldBeTrue()
                }
            }
        }

        should("Get character heropoints") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/heropoints",
                "/responses/characters/heropoints.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val heroPoints = charactersClient.getHeropoints(characterName)

            // then
            heroPoints shouldHaveSize 165
            heroPoints shouldContainAll listOf("0-0", "0-67", "0-113", "0-232")
        }

        should("Get character quests") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/quests",
                "/responses/characters/quests.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val quests = charactersClient.getQuests(characterName)

            // then
            quests shouldHaveSize 108
            quests shouldContainAll listOf(71, 254, 488, 608)
        }

        should("Get character SAB details") {
            // given
            val characterName = "Test Character"
            val escapedName = characterName.replace(" ", "%20")
            stubResponse(
                "/characters/$escapedName/sab",
                "/responses/characters/sab.json",
                apiKey = apiKey,
                schemaVersion = targetSchemaVersion
            )

            // when
            val characterSAB = charactersClient.getSAB(characterName)

            // then
            assertSoftly(characterSAB) {
                zones shouldHaveSize 11
                assertSoftly(zones[4]) {
                    id shouldBe 5
                    mode shouldBe SabZoneMode.NORMAL
                    world shouldBe 2
                    zone shouldBe 2
                }
                assertSoftly(zones[5]) {
                    id shouldBe 13
                    mode shouldBe SabZoneMode.INFANTILE
                    world shouldBe 1
                    zone shouldBe 1
                }
                unlocks shouldHaveSize 8
                assertSoftly(unlocks[0]) {
                    id shouldBe 6
                    name shouldBe "whip"
                }
                // Apparently there's an API bug, where the unlock with ID 10 does not have a name attribute
                // https://wiki.guildwars2.com/wiki/API:2/characters/:id/sab - bottom of the page
                assertSoftly(unlocks[1]) {
                    id shouldBe 10
                    name.shouldBeNull()
                }
                unlocks[4].id shouldBe 18
                songs shouldHaveSize 1
                assertSoftly(songs[0]) {
                    id shouldBe 1
                    name shouldBe "secret_song"
                }
            }
        }
    }
}