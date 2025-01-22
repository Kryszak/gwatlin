package io.github.kryszak.e2e.wvw

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.miscellaneous.GWMiscellaneousClient
import io.github.kryszak.gwatlin.api.wvw.GWWvwClient
import io.kotest.assertions.throwables.shouldNotThrowAny

class WvWE2ETests : BaseE2ESpec() {
    private val client = GWWvwClient()

    init {
        context("WvW") {
            expect("Fetch abilities") {
                val abilityIds = client.getAbilityIds()
                shouldNotThrowAny { client.getAbilities(abilityIds) }
            }
            expect("Fetch matches") {
                val matchIds = client.getMatchIds()
                shouldNotThrowAny { client.getMatches(matchIds) }
            }
            expect("Fetch objectives") {
                val objectiveIds = client.getObjectiveIds().randomElements(100)
                shouldNotThrowAny { client.getObjectives(objectiveIds) }
            }
            expect("Fetch ranks") {
                val rankIds = client.getRankIds().randomElements(100)
                shouldNotThrowAny { client.getRanks(rankIds) }
            }
            expect("Fetch upgrades") {
                val upgradeIds = client.getUpgradeIds()
                shouldNotThrowAny { client.getUpgrades(upgradeIds) }
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