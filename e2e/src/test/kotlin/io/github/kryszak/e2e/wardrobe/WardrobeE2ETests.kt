package io.github.kryszak.e2e.wardrobe

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.wardrobe.GWFinishersClient
import io.github.kryszak.gwatlin.api.wardrobe.GWMountsClient
import io.github.kryszak.gwatlin.api.wardrobe.GWOutfitsClient
import io.github.kryszak.gwatlin.api.wardrobe.GWSkinsClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class WardrobeE2ETests : BaseE2ESpec() {
    init {
        context("Finishers") {
            val client = GWFinishersClient()
            expect("Fetch finishers") {
                val finisherIds = client.getFinisherIds()
                shouldNotThrowAny { client.getFinishers(finisherIds) }
            }
        }
        context("Mounts") {
            val client = GWMountsClient()
            expect("Fetching all mount types") {
                shouldNotThrowAny { client.getAllMountTypes() }
            }
            expect("Fetching all mount skins") {
                shouldNotThrowAny { client.getAllMountSkins() }
            }
        }
        context("Outfits") {
            val client = GWOutfitsClient()
            expect("Fetching random outfits") {
                val outfitIds = client.getOutfitsIds().randomElements(100)
                shouldNotThrowAny { client.getOutfits(outfitIds) }
            }
        }
        context("Skins") {
            val client = GWSkinsClient()
            expect("Fetch skins") {
                val skinIds = client.getSkinIds().randomElements(200)
                shouldNotThrowAny { client.getSkins(skinIds) }
            }
        }
    }
}