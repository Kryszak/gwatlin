package io.github.kryszak.e2e.gamemechanics

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.*
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class GameMechanicsE2ETests : BaseE2ESpec() {
    init {
        context("Colors") {
            val client = GWColorsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch dye colors in $language language") {
                    shouldNotThrowAny { client.getAllDyeColors(language) }
                }
                expect("Fetch paged dye colors in $language") {
                    shouldNotThrowAny { client.getPagedColors(PageRequest(0, 10), language) }
                }
            }
        }
        context("Currency") {
            val client = GWCurrencyClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch currencies in $language language") {
                    shouldNotThrowAny { client.getAllCurrencies(language) }
                }
                expect("Fetch paged currencies in $language") {
                    shouldNotThrowAny { client.getPagedCurrencies(PageRequest(0, 10), language) }
                }
            }
        }
        context("Dungeons") {
            val client = GWDungeonsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch dungeons in $language language") {
                    shouldNotThrowAny { client.getAllDungeons(language) }
                }
            }
        }
        context("Emotes") {
            val client = GWEmotesClient()
            expect("Fetch emotes") {
                shouldNotThrowAny { client.getAllEmotes() }
            }
        }
        context("Legendary armory") {
            val client = GWLegendaryArmoryClient()
            expect("Fetch legendary armories") {
                shouldNotThrowAny { client.getAllLegendaryArmoryItems() }
            }
            expect("Fetch paged legendary armories") {
                shouldNotThrowAny { client.getPagedLegendaryArmoryItems(PageRequest(0, 10)) }
            }
        }
        context("Legends") {
            val client = GWLegendsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching legends list in $language language") {
                    val legendIds = client.getLegendIds()
                    shouldNotThrowAny {
                        client.getLegends(legendIds, language)
                    }
                }
            }
        }
        context("Masteries") {
            val client = GWMasteriesClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching random masteries in $language language") {
                    val masteryIds = client.getMasteriesIds()
                    shouldNotThrowAny {
                        client.getMasteries(masteryIds, language)
                    }
                }
            }
        }
        context("Pets") {
            val client = GWPetsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching pets in $language language") {
                    shouldNotThrowAny { client.getAllPets(language) }
                }
                expect("Fetch paged pets in $language") {
                    shouldNotThrowAny { client.getPagedPets(PageRequest(0, 10), language) }
                }
            }
        }
        context("Professions") {
            val client = GWProfessionsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching professions in $language language") {
                    shouldNotThrowAny { client.getAllProfessions(language) }
                }
            }
        }
        context("Races") {
            val client = GWRacesClient()
            expect("Fetching races") {
                val raceIds = client.getRaceIds()
                shouldNotThrowAny { client.getRaces(raceIds) }
            }
        }
        context("Raids") {
            val client = GWRaidsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch raids in $language language") {
                    shouldNotThrowAny { client.getAllRaids(language) }
                }
            }
        }
        context("Skills") {
            val client = GWSkillsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching random skills in $language language") {
                    val skillIds = client.getSkillIds().randomElements(100)
                    shouldNotThrowAny { client.getSkills(skillIds, language) }
                }
                expect("Fetch paged skills in $language") {
                    shouldNotThrowAny { client.getPagedSkills(PageRequest(0, 10), language) }
                }
            }
        }
        context("Specializations") {
            val client = GWSpecializationClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching specializations in $language language") {
                    val specializationIds = client.getSpecializationIds()
                    shouldNotThrowAny { client.getSpecializations(specializationIds, language) }
                }
                expect("Fetching paged specializations in $language language") {
                    shouldNotThrowAny { client.getPagedSpecializations(PageRequest(0, 10), language) }
                }
            }
        }
        context("Titles") {
            val client = GWTitlesClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch titles in $language language") {
                    shouldNotThrowAny { client.getAllTitles(language) }
                }
                expect("Fetch paged titles in $language language") {
                    shouldNotThrowAny { client.getPagedTitles(PageRequest(0, 10), language) }
                }
            }
        }
        context("Traits") {
            val client = GWTraitsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching random traits in $language language") {
                    val traitIds = client.getTraitIds().randomElements(100)
                    shouldNotThrowAny { client.getTraits(traitIds, language) }
                }
                expect("Fetching paged traits in $language language") {
                    shouldNotThrowAny { client.getPagedTraits(PageRequest(0, 10), language) }
                }
            }
        }
    }
}