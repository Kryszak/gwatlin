package io.github.kryszak.e2e.wardrobe

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.wardrobe.*
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class WardrobeE2ETests : BaseE2ESpec() {
    init {
        ApiLanguage.entries.forEach { language ->
            context("$language language") {
                context("Finishers") {
                    val client = GWFinishersClient()
                    expect("Fetch finishers") {
                        val finisherIds = client.getFinisherIds()
                        shouldNotThrowAny { client.getFinishers(finisherIds, language) }
                    }
                    expect("Fetch paged finishers") {
                        shouldNotThrowAny { client.getPagedFinishers(PageRequest(0, 10), language) }
                    }
                }
                context("Gliders") {
                    val client = GWGlidersClient()
                    expect("Fetch gliders") {
                        val gliderIds = client.getGliderIds()
                        shouldNotThrowAny { client.getGliders(gliderIds, language) }
                    }
                    expect("Fetch paged gliders") {
                        shouldNotThrowAny { client.getPagedGliders(PageRequest(0, 10), language) }
                    }
                }
                context("Jade bots") {
                    val client = GWJadeBotsClient()
                    expect("Fetch jade bots") {
                        val jadeBotIds = client.getJadeBotIds()
                        shouldNotThrowAny { client.getJadeBots(jadeBotIds, language) }
                    }
                }
                context("Mail carriers") {
                    val client = GWMailCarriersClient()
                    expect("Fetch carriers") {
                        val mailCarrierIds = client.getMailCarrierIds()
                        shouldNotThrowAny { client.getMailCarriers(mailCarrierIds, language) }
                    }
                    expect("Fetch paged carriers") {
                        shouldNotThrowAny { client.getPagedMailCarriers(PageRequest(0, 10), language) }
                    }
                }
                context("Minis") {
                    val client = GWMinisClient()
                    ApiLanguage.entries.forEach { language ->
                        expect("Fetch all minis") {
                            shouldNotThrowAny { client.getAllMinis(language) }
                        }
                        expect("Fetch paged minis") {
                            shouldNotThrowAny { client.getPagedMinis(PageRequest(0, 10), language) }
                        }
                    }
                }
                context("Mounts") {
                    val client = GWMountsClient()
                    expect("Fetching all mount types") {
                        shouldNotThrowAny { client.getAllMountTypes(language) }
                    }
                    expect("Fetching all mount skins") {
                        shouldNotThrowAny { client.getAllMountSkins(language) }
                    }
                    expect("Fetching paged mount skins") {
                        shouldNotThrowAny { client.getPagedMountSkins(PageRequest(0, 10), language) }
                    }
                }
                context("Novelties") {
                    val client = GWNoveltiesClient()
                    expect("Fetch all novelties") {
                        shouldNotThrowAny { client.getAllNovelties(language) }
                    }
                    expect("Fetch paged novelties") {
                        shouldNotThrowAny { client.getPagedNovelties(PageRequest(0, 10), language) }
                    }
                }
                context("Outfits") {
                    val client = GWOutfitsClient()
                    expect("Fetch random outfits") {
                        val outfitIds = client.getOutfitsIds().randomElements(100)
                        shouldNotThrowAny { client.getOutfits(outfitIds, language) }
                    }
                }
                context("Skiffs") {
                    val client = GWSkiffsClient()
                    expect("Fetch skiffs") {
                        val skiffIds = client.getSkiffIds()
                        shouldNotThrowAny { client.getSkiffs(skiffIds, language) }
                    }
                }
                context("Skins") {
                    val client = GWSkinsClient()
                    expect("Fetch skins") {
                        val skinIds = client.getSkinIds().randomElements(200)
                        shouldNotThrowAny { client.getSkins(skinIds, language) }
                    }
                    expect("Fetch pages skins") {
                        shouldNotThrowAny { client.getPagedSkins(PageRequest(0, 10), language) }
                    }
                }
            }
        }
    }
}