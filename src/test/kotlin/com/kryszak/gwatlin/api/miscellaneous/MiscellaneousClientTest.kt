package com.kryszak.gwatlin.api.miscellaneous

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class MiscellaneousClientTest : WiremockTest() {

    private val miscellaneousClient = GWMiscellaneousClient()

    init {
        should("Get build id") {
            // given
            stubResponse("/build", "/responses/miscellaneous/build_id.json")

            // when
            val buildId = miscellaneousClient.getBuildId()

            // then
            buildId shouldBe 45075
        }

        should("Get icons") {
            // given
            stubResponse("/files?ids=all", "/responses/miscellaneous/icons.json")

            // when
            val icons = miscellaneousClient.getIcons()

            // then
            assertSoftly(icons[0]) {
                id shouldBe "map_complete"
                icon shouldBe "https://render.guildwars2.com/file/5A4E663071250EC72668C09E3C082E595A380BF7/528724.png"
            }
        }

        should("Get quaggans") {
            // given
            stubResponse("/quaggans?ids=all", "/responses/miscellaneous/quaggans.json")

            // when
            val quaggans = miscellaneousClient.getQuaggans()

            // then
            quaggans.size shouldBe 35
        }

        should("Get dye colors") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/colors?ids=all", "/responses/miscellaneous/dye_colors.json", lang)

            // when
            val colors = miscellaneousClient.getDyeColors(lang)

            // then
            assertSoftly(colors[1]) {
                id shouldBe 2
                name shouldBe "Black"
                baseRgb shouldContainExactly listOf(128, 26, 26)
                assertSoftly(cloth) {
                    brightness shouldBe -13
                    contrast shouldBe 1
                    hue shouldBe 275
                    saturation shouldBe 0.0234375
                    lightness shouldBe 1.09375
                    rgb shouldContainExactly listOf(37, 35, 38)
                }
                fur.shouldNotBeNull()
                item shouldBe 20358
                categories shouldContainExactly listOf("Gray", "Metal", "Rare")
            }
        }

        should("Get currencies") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/currencies?ids=all", "/responses/miscellaneous/currencies.json", lang)

            // when
            val currencies = miscellaneousClient.getCurrencies(lang)

            // then
            assertSoftly(currencies[0]) {
                id shouldBe 1
                name shouldBe "Coin"
                description shouldBe "The primary currency of Tyria. Spent at vendors throughout the world."
                order shouldBe 101
                icon shouldBe "https://render.guildwars2.com/file/98457F504BA2FAC8457F532C4B30EDC23929ACF9/619316.png"
            }
        }

        should("Get dungeons") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/dungeons?ids=all", "/responses/miscellaneous/dungeons.json", lang)

            // when
            val dungeons = miscellaneousClient.getDungeons(lang)

            // then
            assertSoftly(dungeons[0]) {
                id shouldBe "ascalonian_catacombs"
                assertSoftly(paths[0]) {
                    id shouldBe "ac_story"
                    type shouldBe "Story"
                }
            }
        }

        should("Get minis") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/minis?ids=all", "/responses/miscellaneous/minis.json", lang)

            // when
            val minis = miscellaneousClient.getMinis(lang)

            // then
            assertSoftly(minis[0]) {
                id shouldBe 1
                name shouldBe "Mini Rytlock"
                icon shouldBe "https://render.guildwars2.com/file/795ED1B945A29EC3E3066797DF57FFB25ABAA631/340551.png"
                order shouldBe 1
                itemId shouldBe 21047
            }
        }

        should("Get novelties") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/novelties?ids=all", "/responses/miscellaneous/novelties.json", lang)

            // when
            val novelties = miscellaneousClient.getNovelties(lang)

            // then
            assertSoftly(novelties[0]) {
                id shouldBe 1
                name shouldBe "Embellished Kite"
                description shouldBe "<c=@abilitytype>Held Item.</c> Equip a bundle for decoration or to use noncombat skills."
                icon shouldBe "https://render.guildwars2.com/file/7B043D640ED57517051D5FC038D7CDDDE5F82933/2015154.png"
                slot shouldBe "HeldItem"
                unlockItem shouldContainExactly listOf(88124)
            }
        }

        should("Get raids") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/raids?ids=all", "/responses/miscellaneous/raids.json", lang)

            // when
            val raids = miscellaneousClient.getRaids(lang)

            // then
            assertSoftly(raids[0]) {
                id shouldBe "forsaken_thicket"
                assertSoftly(wings[0]) {
                    id shouldBe "spirit_vale"
                    assertSoftly(events[0]) {
                        id shouldBe "vale_guardian"
                        type shouldBe "Boss"
                    }
                }
            }
        }

        should("Get titles") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/titles?ids=all", "/responses/miscellaneous/titles.json", lang)

            // when
            val titles = miscellaneousClient.getTitles(lang)

            // then
            assertSoftly(titles[0]) {
                id shouldBe 1
                name shouldBe "Traveler"
                achievement shouldBe 111
                achievements shouldContainExactly listOf(111)
            }
        }

        should("Get worlds") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/worlds?ids=all", "/responses/miscellaneous/worlds.json", lang)

            // when
            val worlds = miscellaneousClient.getWorlds(lang)

            // then
            assertSoftly(worlds[0]) {
                id shouldBe 1001
                name shouldBe "Anvil Rock"
                population shouldBe "High"
            }
        }
    }
}