package io.github.kryszak.e2e.wvw

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.miscellaneous.GWMiscellaneousClient
import io.github.kryszak.gwatlin.api.wvw.GWWvwClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class WvWE2ETests : BaseE2ESpec() {
    private val client = GWWvwClient()

    init {
        context("WvW") {
            ApiLanguage.entries.forEach { language ->
                context("$language language") {
                    expect("Fetch abilities") {
                        val abilityIds = client.getAbilityIds()
                        shouldNotThrowAny { client.getAbilities(abilityIds, language) }
                    }
                    expect("Fetch objectives") {
                        val objectiveIds = client.getObjectiveIds().randomElements(100)
                        shouldNotThrowAny { client.getObjectives(objectiveIds, language) }
                    }
                    expect("Fetch ranks") {
                        val rankIds = client.getRankIds().randomElements(100)
                        shouldNotThrowAny { client.getRanks(rankIds, language) }
                    }
                    expect("Fetch upgrades") {
                        val upgradeIds = client.getUpgradeIds()
                        shouldNotThrowAny { client.getUpgrades(upgradeIds, language) }
                    }
                }
            }
            expect("Fetch matches") {
                val matchIds = client.getMatchIds()
                shouldNotThrowAny { client.getMatches(matchIds) }
            }
        }
        context("Match") {
            val miscellaneousClient = GWMiscellaneousClient()
            val worldId = miscellaneousClient.getWorlds().random().id
            expect("Get overview") {
                shouldNotThrowAny { client.getMatchesOverview(worldId) }
            }
            expect("Get score") {
                shouldNotThrowAny { client.getMatchScores(worldId) }
            }
            expect("Get stats") {
                shouldNotThrowAny { client.getMatchStats(worldId) }
            }
        }
    }
}