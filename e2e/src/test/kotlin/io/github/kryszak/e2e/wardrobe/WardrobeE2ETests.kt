package io.github.kryszak.e2e.wardrobe

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.*
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class WardrobeE2ETests : BaseE2ESpec() {
    init {
        context("Finishers") {
            val client = GWFinishersClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch finishers in $language language") {
                    val finisherIds = client.getFinisherIds()
                    shouldNotThrowAny { client.getFinishers(finisherIds, language) }
                }
            }
        }
        context("Gliders") {
            val client = GWGlidersClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch gliders in $language language") {
                    val gliderIds = client.getGliderIds()
                    shouldNotThrowAny { client.getGliders(gliderIds, language) }
                }
            }
        }
        context("Jade bots") {
            val client = GWJadeBotsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch jade bots in $language language") {
                    val jadeBotIds = client.getJadeBotIds()
                    shouldNotThrowAny { client.getJadeBots(jadeBotIds, language) }
                }
            }
        }
        context("Mail carriers") {
            val client = GWMailCarriersClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch carriers in $language language") {
                    val mailCarrierIds = client.getMailCarrierIds()
                    shouldNotThrowAny { client.getMailCarriers(mailCarrierIds, language) }
                }
            }
        }
        context("Minis") {
            val client = GWMinisClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch all minis in $language language") {
                    shouldNotThrowAny { client.getAllMinis(language) }
                }
            }
        }
        context("Mounts") {
            val client = GWMountsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching all mount types in $language language") {
                    shouldNotThrowAny { client.getAllMountTypes(language) }
                }
            }
            ApiLanguage.entries.forEach { language ->
                expect("Fetching all mount skins in $language language") {
                    shouldNotThrowAny { client.getAllMountSkins(language) }
                }
            }
        }
        context("Novelties") {
            val client = GWNoveltiesClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch all novelties in $language language") {
                    shouldNotThrowAny { client.getAllNovelties(language) }
                }
            }
        }
        context("Outfits") {
            val client = GWOutfitsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetching random outfits in $language language") {
                    val outfitIds = client.getOutfitsIds().randomElements(100)
                    shouldNotThrowAny { client.getOutfits(outfitIds, language) }
                }
            }
        }
        context("Skins") {
            val client = GWSkinsClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch skins in $language language") {
                    val skinIds = client.getSkinIds().randomElements(200)
                    shouldNotThrowAny { client.getSkins(skinIds, language) }
                }
            }
        }
    }
}