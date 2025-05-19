package io.github.kryszak.e2e.gamemechanics

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.gamemechanics.*
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class GameMechanicsE2ETests : BaseE2ESpec() {
    init {
        ApiLanguage.entries.forEach { language ->
            context("$language language") {
                context("Colors") {
                    val client = GWColorsClient()
                    expect("Fetch dye colors") {
                        shouldNotThrowAny { client.getAllDyeColors(language) }
                    }
                    expect("Fetch paged dye colors") {
                        shouldNotThrowAny { client.getPagedColors(PageRequest(0, 10), language) }
                    }
                }
                context("Currency") {
                    val client = GWCurrencyClient()
                    expect("Fetch currencies") {
                        shouldNotThrowAny { client.getAllCurrencies(language) }
                    }
                    expect("Fetch paged currencies") {
                        shouldNotThrowAny { client.getPagedCurrencies(PageRequest(0, 10), language) }
                    }
                }
                context("Dungeons") {
                    val client = GWDungeonsClient()
                    expect("Fetch dungeons") {
                        shouldNotThrowAny { client.getAllDungeons(language) }
                    }
                }
                context("Legends") {
                    val client = GWLegendsClient()
                    expect("Fetching legends list") {
                        val legendIds = client.getLegendIds()
                        shouldNotThrowAny {
                            client.getLegends(legendIds, language)
                        }
                    }
                }
                context("Masteries") {
                    val client = GWMasteriesClient()
                    expect("Fetching random masteries") {
                        val masteryIds = client.getMasteriesIds()
                        shouldNotThrowAny {
                            client.getMasteries(masteryIds, language)
                        }
                    }
                }
                context("Pets") {
                    val client = GWPetsClient()
                    expect("Fetching pets") {
                        shouldNotThrowAny { client.getAllPets(language) }
                    }
                    expect("Fetch paged pets") {
                        shouldNotThrowAny { client.getPagedPets(PageRequest(0, 10), language) }
                    }
                }
                /**
                 * For some reason no matter the timeout this request takes a long time
                 * That's why test retries 3 times
                 */
                context("Professions") {
                    val client = GWProfessionsClient()
                    expect("Fetching professions") {
                        var retryCount = 0
                        shouldNotThrowAny {
                            while (true) {
                                try {
                                    client.getAllProfessions(language)
                                    break
                                } catch (e: ApiRequestException) {
                                    if (retryCount > 2) {
                                        throw e
                                    }
                                    retryCount++
                                }
                            }
                        }
                    }
                }
                context("Raids") {
                    val client = GWRaidsClient()
                    expect("Fetch raids") {
                        shouldNotThrowAny { client.getAllRaids(language) }
                    }
                }
                context("Skills") {
                    val client = GWSkillsClient()
                    expect("Fetching random skills") {
                        val skillIds = client.getSkillIds().randomElements(100)
                        shouldNotThrowAny { client.getSkills(skillIds, language) }
                    }
                    expect("Fetch paged skills") {
                        shouldNotThrowAny { client.getPagedSkills(PageRequest(0, 10), language) }
                    }
                }
                context("Specializations") {
                    val client = GWSpecializationClient()
                    expect("Fetching specializations") {
                        val specializationIds = client.getSpecializationIds()
                        shouldNotThrowAny { client.getSpecializations(specializationIds, language) }
                    }
                    expect("Fetching paged specializations") {
                        shouldNotThrowAny { client.getPagedSpecializations(PageRequest(0, 10), language) }
                    }
                }
                context("Titles") {
                    val client = GWTitlesClient()
                    expect("Fetch titles") {
                        shouldNotThrowAny { client.getAllTitles(language) }
                    }
                    expect("Fetch paged titles") {
                        shouldNotThrowAny { client.getPagedTitles(PageRequest(0, 10), language) }
                    }
                }
                context("Traits") {
                    val client = GWTraitsClient()
                    expect("Fetching random traits") {
                        val traitIds = client.getTraitIds().randomElements(100)
                        shouldNotThrowAny { client.getTraits(traitIds, language) }
                    }
                    expect("Fetching paged traits") {
                        shouldNotThrowAny { client.getPagedTraits(PageRequest(0, 10), language) }
                    }
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
        context("Races") {
            val client = GWRacesClient()
            expect("Fetching races") {
                val raceIds = client.getRaceIds()
                shouldNotThrowAny { client.getRaces(raceIds) }
            }
        }
    }
}