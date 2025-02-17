package io.github.kryszak.e2e.miscellaneous

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.miscellaneous.GWApiInfoClient
import io.github.kryszak.gwatlin.api.miscellaneous.GWMiscellaneousClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class MiscellaneousE2ETests : BaseE2ESpec() {

    init {
        context("Api info") {
            val client = GWApiInfoClient()
            expect("Api information is fetched") {
                shouldNotThrowAny { client.getSchemaVersions() }
            }
        }
        context("Miscellaneous") {
            val client = GWMiscellaneousClient()
            expect("Fetch build id") {
                shouldNotThrowAny { client.getBuildId() }
            }
            expect("Fetch icons") {
                shouldNotThrowAny { client.getIcons() }
            }
            expect("Fetch quaggans") {
                shouldNotThrowAny { client.getQuaggans() }
            }
            ApiLanguage.entries.forEach { language ->
                context("$language language") {
                    expect("Fetch worlds") {
                        shouldNotThrowAny { client.getWorlds(language) }
                    }
                }
            }
        }
    }
}