package io.github.kryszak.e2e.pvp

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.pvp.GWPvPClient
import io.github.kryszak.gwatlin.api.pvp.GWPvpAmuletsClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class PvPE2ETests : BaseE2ESpec() {
    private val client = GWPvPClient()

    init {
        context("Pvp Amulets") {
            val client = GWPvpAmuletsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch pvp amulets in $language language") {
                    val pvpAmuletIds = client.getPvpAmuletIds()
                    shouldNotThrowAny { client.getPvpAmulets(pvpAmuletIds, language) }
                }
            }
        }
        context("PvP Unauthenticated") {
            ApiLanguage.entries.forEach { language ->
                expect("Fetch ranks in $language language") {
                    val rankIds = client.getPvpRankIds()
                    shouldNotThrowAny { client.getPvpRanks(rankIds, language) }
                }
            }
            ApiLanguage.entries.forEach { language ->
                expect("Fetch seasons in $language language") {
                    val seasonIds = client.getPvpSeasonIds().randomElements(10)
                    shouldNotThrowAny { client.getPvpSeasons(seasonIds, language) }
                }
            }
        }
    }
}