package io.github.kryszak.e2e.wardrobe

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.wardrobe.GWFinishersClient
import io.github.kryszak.gwatlin.api.wardrobe.GWMountsClient
import io.github.kryszak.gwatlin.api.wardrobe.GWOutfitsClient
import io.github.kryszak.gwatlin.api.wardrobe.GWSkinsClient
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