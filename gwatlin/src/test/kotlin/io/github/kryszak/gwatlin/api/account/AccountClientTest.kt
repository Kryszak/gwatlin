package io.github.kryszak.gwatlin.api.account

import io.github.kryszak.gwatlin.api.characters.model.character.Specialization
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class AccountClientTest : BaseWiremockTest() {
    private val apiKey = "1234"

    private val accountClient = GWAccountClient(apiKey)

    init {
        should("Get account details") {
            // given
            stubResponse("/v2/account", "/responses/account/account.json", apiKey = apiKey)

            // when
            val account = accountClient.getAccountDetails()

            // then
            assertSoftly(account) {
                id shouldBe "C19467C6-F5AD-E211-8756-78E7D1936222"
                name shouldBe "Account.1234"
                age shouldBe 22911780
                world shouldBe 1004
                guilds shouldHaveSize 5
                guildLeader shouldHaveSize 1
                created shouldBe "2013-04-25T22:09:00Z"
                access shouldHaveSize 3
                commander
                fractalLevel shouldBe 100
                dailyAp shouldBe 7659
                monthlyAp shouldBe 1129
                wvwRank shouldBe 514
            }
        }

        should("Get account achievements") {
            // given
            stubResponse("/v2/account/achievements", "/responses/account/achievements.json", apiKey = apiKey)

            // given
            val achievements = accountClient.getAccountAchievements()

            // then
            assertSoftly(achievements[2]) {
                id shouldBe 1653
                bits shouldHaveSize 4
                current shouldBe 4
                max shouldBe 30
                !done
            }
        }

        should("Get account vault") {
            // given
            stubResponse("/v2/account/bank", "/responses/account/bank.json", apiKey = apiKey)

            // given
            val vault = accountClient.getAccountVault()

            // then
            vault shouldHaveSize 6
            assertSoftly(vault[3]!!) {
                id shouldBe 46760
                count shouldBe 1
                upgrades shouldHaveSize 1
                infusions shouldHaveSize 1
            }
        }

        should("Get account daily crafting") {
            // given
            stubResponse("/v2/account/dailycrafting", "/responses/account/dailycrafting.json", apiKey = apiKey)

            // when
            val dailyCrafting = accountClient.getAccountDailyCrafting()

            // then
            dailyCrafting shouldContainExactly listOf(
                "lump_of_mithrilium",
                "spool_of_silk_weaving_thread",
                "spool_of_thick_elonian_cord"
            )
        }

        should("Get done daily dungeons") {
            /// given
            stubResponse("/v2/account/dungeons", "/responses/account/dungeons.json", apiKey = apiKey)

            // when
            val dungeons = accountClient.getCompletedDailyDungeons()

            // then
            dungeons shouldContainExactly listOf("hodgins", "seraph", "seer")
        }

        should("Get unlocked dyes") {
            // given
            stubResponse("/v2/account/dyes", "/responses/account/dyes.json", apiKey = apiKey)

            // when
            val dyes = accountClient.getUnlockedDyes()

            // then
            dyes shouldContainExactly listOf(3, 4, 5, 6)
        }

        should("Get account finishers") {
            // given
            stubResponse("/v2/account/finishers", "/responses/account/finishers.json", apiKey = apiKey)

            // when
            val finishers = accountClient.getFinishers()

            // then
            assertSoftly(finishers[1]) {
                id shouldBe 15
                permanent.shouldBeFalse()
                quantity shouldBe 5
            }
        }

        should("Get account gliders") {
            // given
            stubResponse("/v2/account/gliders", "/responses/account/gliders.json", apiKey = apiKey)

            // when
            val gliders = accountClient.getGliders()

            // then
            gliders shouldContainExactly listOf(1, 2, 3)
        }

        should("Get account cats") {
            // given
            stubResponse("/v2/account/home/cats", "/responses/account/cats.json", apiKey = apiKey)

            // when
            val cats = accountClient.getCats()

            // then
            assertSoftly(cats[0]) {
                id shouldBe 2
                hint shouldBe "grilled_chicken"
            }
        }

        should("Get unlocked home instance nodes") {
            // given
            stubResponse("/v2/account/home/nodes", "/responses/account/nodes.json", apiKey = apiKey)

            // when
            val nodes = accountClient.getNodes()

            // then
            nodes shouldContainExactly listOf(
                "quartz_node",
                "krait_obelisk",
                "sprocket_generator",
                "wintersday_tree",
                "bandit_chest",
                "aurilium_node",
                "exalted_chest",
                "airship_cargo",
                "crystallized_supply_cache"
            )
        }

        should("Get account inventory") {
            // given
            stubResponse("/v2/account/inventory", "/responses/account/inventory.json", apiKey = apiKey)

            // when
            val inventory = accountClient.getInventory()

            // then
            assertSoftly(inventory) {
                size shouldBe 3
                assertSoftly(it[0]!!) {
                    id shouldBe 44602
                    count shouldBe 1
                    binding shouldBe "Account"
                }
                it[2] shouldBe null
            }
        }

        should("Get account luck") {
            // given
            stubResponse("/v2/account/luck", "/responses/account/luck.json", apiKey = apiKey)

            // when
            val luck = accountClient.getLuck()

            // then
            assertSoftly(luck) {
                id shouldBe "luck"
                value shouldBe 4295449
            }
        }

        should("Get account mail carriers") {
            // given
            stubResponse("/v2/account/mailcarriers", "/responses/account/mailcarriers.json", apiKey = apiKey)

            // when
            val mailcarriers = accountClient.getMailCarriers()

            // then
            mailcarriers shouldContainExactly listOf(1, 2, 3)
        }

        should("Get map chests") {
            // given
            stubResponse("/v2/account/mapchests", "/responses/account/mapchests.json", apiKey = apiKey)

            // when
            val mapchests = accountClient.getMapChests()

            // then
            mapchests shouldContainExactly listOf("verdant_brink_heros_choice_chest")
        }

        should("Get account masteries") {
            // given
            stubResponse("/v2/account/masteries", "/responses/account/masteries.json", apiKey = apiKey)

            // when
            val masteries = accountClient.getMasteries()

            // then
            assertSoftly(masteries[0]) {
                id shouldBe 1
                level shouldBe 4
            }
        }

        should("Get account mastery details") {
            // given
            stubResponse("/v2/account/mastery/points", "/responses/account/mastery_points.json", apiKey = apiKey)

            // when
            val masteryDetails = accountClient.getMasteryDetails()

            // then
            assertSoftly(masteryDetails) {
                unlocked shouldHaveSize 2
                assertSoftly(totals[0]) {
                    region shouldBe "Tyria"
                    spent shouldBe 49
                    earned shouldBe 63
                }
            }
        }

        should("Get account materials") {
            // given
            stubResponse("/v2/account/materials", "/responses/account/materials.json", apiKey = apiKey)

            // when
            val materials = accountClient.getMaterials()

            // then
            assertSoftly(materials[0]) {
                id shouldBe 12134
                category shouldBe 5
                count shouldBe 64
            }
        }

        should("Get account minis") {
            // given
            stubResponse("/v2/account/minis", "/responses/account/minis.json", apiKey = apiKey)

            //when 
            val minis = accountClient.getMinis()

            // then
            minis shouldContainExactly listOf(1, 65, 67, 80, 86, 93, 100)
        }

        should("Get unlocked mount skins") {
            // given
            stubResponse("/v2/account/mounts/skins", "/responses/account/mount_skins.json", apiKey = apiKey)

            // when
            val mountSkins = accountClient.getMountSkins()

            // then
            mountSkins shouldContainExactly listOf(1, 2, 3, 4, 6, 103)
        }

        should("Get unlocked mount types") {
            // given
            stubResponse("/v2/account/mounts/types", "/responses/account/mount_types.json", apiKey = apiKey)

            // when
            val mountTypes = accountClient.getMountTypes()

            // then
            mountTypes shouldContainExactly listOf(
                "raptor",
                "skimmer",
                "springer",
                "griffon",
                "jackal",
                "roller_beetle"
            )
        }

        should("Get novelties") {
            // given
            stubResponse("/v2/account/novelties", "/responses/account/novelties.json", apiKey = apiKey)

            // when
            val novelties = accountClient.getNovelties()

            // then
            novelties shouldContainExactly listOf(1, 2, 3)
        }

        should("Get outfits") {
            // given
            stubResponse("/v2/account/outfits", "/responses/account/outfits.json", apiKey = apiKey)

            // when
            val outfits = accountClient.getOutfits()

            // then
            outfits shouldContainExactly listOf(5, 27, 44)
        }

        should("Get pvp heroes") {
            // given
            stubResponse("/v2/account/pvp/heroes", "/responses/account/pvp_heroes.json", apiKey = apiKey)

            // when
            val pvpHeroes = accountClient.getPvpHeroes()

            // then
            pvpHeroes shouldContainExactly listOf(1, 2, 3)
        }

        should("Get raids") {
            // given
            stubResponse("/v2/account/raids", "/responses/account/raids.json", apiKey = apiKey)

            // when
            val raids = accountClient.getRaids()

            // then
            raids shouldContainExactly listOf("gorseval", "xera")
        }

        should("Get recipes") {
            // given
            stubResponse("/v2/account/recipes", "/responses/account/recipes.json", apiKey = apiKey)

            // when
            val recipes = accountClient.getRecipes()

            // then
            recipes shouldContainExactly listOf(104, 105, 11888, 11889)
        }

        should("Get skins") {
            // given
            stubResponse("/v2/account/skins", "/responses/account/skins.json", apiKey = apiKey)

            // when
            val skins = accountClient.getSkins()

            // then
            skins shouldContainExactly listOf(1, 2, 3)
        }

        should("Get titles") {
            // given
            stubResponse("/v2/account/titles", "/responses/account/titles.json", apiKey = apiKey)

            // when
            val titles = accountClient.getTitles()

            // then
            titles shouldContainExactly listOf(11, 12, 13, 190, 204, 213)
        }

        should("Get wallet") {
            // given
            stubResponse("/v2/account/wallet", "/responses/account/wallet.json", apiKey = apiKey)

            // when
            val wallet = accountClient.getWallet()

            // then
            assertSoftly(wallet[0]) {
                id shouldBe 1
                value shouldBe 100001
            }
        }

        should("Get world bosses") {
            // given
            stubResponse("/v2/account/worldbosses", "/responses/account/worldbosses.json", apiKey = apiKey)

            // when
            val worldbosses = accountClient.getWorldBosses()

            // then
            worldbosses shouldContainExactly listOf("admiral_taidha_covington")
        }

        should("Get build storage") {
            // given
            stubResponse("/v2/account/buildstorage", "/responses/account/buildstorage.json", apiKey = apiKey)

            // when
            val buildStorages = accountClient.getBuildStorage()

            // then
            assertSoftly(buildStorages) {
                it shouldHaveSize 1
                assertSoftly(it[0]) {
                    name shouldBe "test"
                    profession shouldBe "Elementalist"
                    specializations shouldBe Specialization(12, setOf(10, 11))
                    skills.shouldBeNull()
                    aquaticSkills.shouldBeEmpty()
                    legends.shouldBeEmpty()
                    aquaticLegends.shouldBeEmpty()
                }
            }
        }

        should("Get emotes") {
            // given
            stubResponse("/v2/account/emotes", "/responses/account/emotes.json", apiKey = apiKey)

            // when
            val emotes = accountClient.getEmotes()

            // then
            emotes shouldContainExactly listOf("geargrind", "step", "shuffle", "rockout")
        }

        should("Get homestead decorations") {
            // given
            stubResponse(
                "/v2/account/homestead/decorations",
                "/responses/account/homestead/decorations.json",
                apiKey = apiKey
            )

            // when
            val decorations = accountClient.getHomesteadDecorations()

            // then
            assertSoftly(decorations) {
                it shouldHaveSize 5
                assertSoftly(it[0]) {
                    id shouldBe 35
                    count shouldBe 92
                }
            }
        }

        should("Get homestead glyphs") {
            // given
            stubResponse("/v2/account/homestead/glyphs", "/responses/account/homestead/glyphs.json", apiKey = apiKey)

            // when
            val glyphs = accountClient.getHomesteadGlyphs()

            // then
            glyphs shouldContainExactly listOf("volatility_harvesting", "volatility_logging", "volatility_mining")
        }

        should("Get jade bots") {
            // given
            stubResponse("/v2/account/jadebots", "/responses/account/jadebots.json", apiKey = apiKey)

            // when
            val jadeBots = accountClient.getJadeBots()

            // then
            jadeBots shouldContainExactly listOf(3, 4)
        }

        should("Get legendary armory") {
            // given
            stubResponse("/v2/account/legendaryarmory", "/responses/account/legendaryarmory.json", apiKey = apiKey)

            // when
            val legendaryArmory = accountClient.getLegendaryArmory()

            // then
            assertSoftly(legendaryArmory) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe 81462
                    count shouldBe 1
                }
            }
        }

        should("Get progression") {
            // given
            stubResponse("/v2/account/progression", "/responses/account/progression.json", apiKey = apiKey)

            // when
            val progression = accountClient.getProgression()

            // then
            assertSoftly(progression) {
                it shouldHaveSize 4
                assertSoftly(it[0]) {
                    id shouldBe "fractal_agony_impedance"
                    value shouldBe 2
                }
            }
        }

        should("Get skiffs") {
            // given
            stubResponse("/v2/account/skiffs", "/responses/account/skiffs.json", apiKey = apiKey)

            // when
            val skiffs = accountClient.getSkiffs()

            // then
            skiffs shouldContainExactly listOf(410, 413, 428, 435, 502, 539)
        }
    }
}