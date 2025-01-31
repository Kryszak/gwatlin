package io.github.kryszak.e2e.wizardsvault

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.wizardsvault.GWWizardsVaultClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class WizardsVaultE2ETests : BaseE2ESpec() {

    private val client = GWWizardsVaultClient()

    init {
        context("Season") {
            expect("Fetch season") {
                shouldNotThrowAny { client.getSeason() }
            }
        }
        context("Listings") {
            expect("Fetch listings") {
                val listingIds = client.getListingIds()
                shouldNotThrowAny { client.getListings(listingIds) }
            }
        }
        context("Objectives") {
            expect("Fetch objectives") {
                val objectiveIds = client.getObjectiveIds().randomElements(100)
                shouldNotThrowAny { client.getObjectives(objectiveIds) }
            }
        }
    }
}