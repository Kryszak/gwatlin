package io.github.kryszak.e2e.homeinstance

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.gwatlin.api.homeinstance.GWHomeInstanceClient
import io.kotest.assertions.throwables.shouldNotThrowAny

class HomeInstanceE2ETests : BaseE2ESpec() {
    private val client = GWHomeInstanceClient()

    init {
        context("Home instance") {
            expect("Fetch cats") {
                val catIds = client.getCatIds()
                shouldNotThrowAny { client.getCats(catIds) }
            }
            expect("Fetch nodes") {
                shouldNotThrowAny { client.getNodeIds() }
            }
        }
    }
}