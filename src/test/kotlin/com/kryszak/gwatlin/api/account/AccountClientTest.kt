package com.kryszak.gwatlin.api.account

import com.kryszak.gwatlin.config.WiremockTestKt
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class AccountClientTest : WiremockTestKt() {
    private val apiKey = "1234"

    private val accountClient = GWAccountClient(apiKey)

    init {
        should("Get account details") {
            // given
            stubAuthResponse("/account", "/responses/account/account.json", apiKey)

            // when
            val account = accountClient.getAccountDetails()

            // then
            assertSoftly(account) {
                id shouldBe "C19467C6-F5AD-E211-8756-78E7D1936222"
                name shouldBe "Account.1234"
                age shouldBe 22911780
                world shouldBe 1004
                guilds.size shouldBe 5
                guildLeader.size shouldBe 1
                created shouldBe "2013-04-25T22:09:00Z"
                access.size shouldBe 3
                commander
                fractalLevel shouldBe 100
                dailyAp shouldBe 7659
                monthlyAp shouldBe 1129
                wvwRank shouldBe 514
            }
        }

        should("Get account achievements") {
            // given
            stubAuthResponse("/account/achievements", "/responses/account/achievements.json", apiKey)

            // given
            val achievements = accountClient.getAccountAchievements()

            // then
            assertSoftly(achievements[2]) {
                id shouldBe 1653
                bits?.size shouldBe 4
                current shouldBe 4
                max shouldBe 30
                !done
            }
        }

        should("Get account vault") {
            // given
            stubAuthResponse("/account/bank", "/responses/account/bank.json", apiKey)

            // given
            val vault = accountClient.getAccountVault()

            // then
            vault.size shouldBe 6
            assertSoftly(vault[3]!!) {
                id shouldBe 46760
                count shouldBe 1
                upgrades?.size shouldBe 1
                infusions?.size shouldBe 1
            }
        }

        should("Get account daily crafting") {
            // given
            stubAuthResponse("/account/dailycrafting", "/responses/account/dailycrafting.json", apiKey)

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
            stubAuthResponse("/account/dungeons", "/responses/account/dungeons.json", apiKey)

            // when
            val dungeons = accountClient.getCompletedDailyDungeons()

            // then
            dungeons shouldContainExactly listOf("hodgins", "seraph", "seer")
        }

        should("Get unlocked dyes") {
            // given
            stubAuthResponse("/account/dyes", "/responses/account/dyes.json", apiKey)

            // when
            val dyes = accountClient.getUnlockedDyes()

            // then
            dyes shouldContainExactly listOf(3, 4, 5, 6)
        }

        should("Get account finishers") {
            // given
            stubAuthResponse("/account/finishers", "/responses/account/finishers.json", apiKey)

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
            stubAuthResponse("/account/gliders", "/responses/account/gliders.json", apiKey)

            // when
            val gliders = accountClient.getGliders()

            // then
            gliders shouldContainExactly listOf(1, 2, 3)
        }

        should("Get account cats") {
            // given
            stubAuthResponse("/account/home/cats", "/responses/account/cats.json", apiKey)

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
            stubAuthResponse("/account/home/nodes", "/responses/account/nodes.json", apiKey)

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
            stubAuthResponse("/account/inventory", "/responses/account/inventory.json", apiKey)

            // when
            val inventory = accountClient.getInventory()

            // then
            assertSoftly(inventory[0]) {
                id shouldBe 44602
                count shouldBe 1
                binding shouldBe "Account"
            }
        }

        should("Get account luck") {
            // given
            stubAuthResponse("/account/luck", "/responses/account/luck.json", apiKey)

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
            stubAuthResponse("/account/mailcarriers", "/responses/account/mailcarriers.json", apiKey)

            // when
            val mailcarriers = accountClient.getMailCarriers()

            // then
            mailcarriers shouldContainExactly listOf(1, 2, 3)
        }

        should("Get map chests") {
            // given
            stubAuthResponse("/account/mapchests", "/responses/account/mapchests.json", apiKey)

            // when
            val mapchests = accountClient.getMapChests()

            // then
            mapchests shouldContainExactly listOf("verdant_brink_heros_choice_chest")
        }

        should("Get account masteries") {
            // given
            stubAuthResponse("/account/masteries", "/responses/account/masteries.json", apiKey)

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
            stubAuthResponse("/account/mastery/points", "/responses/account/mastery_points.json", apiKey)

            // when
            val masteryDetails = accountClient.getMasteryDetails()

            // then
            assertSoftly(masteryDetails) {
                unlocked.size shouldBe 2
                assertSoftly(totals[0]) {
                    region shouldBe "Tyria"
                    spent shouldBe 49
                    earned shouldBe 63
                }
            }
        }

        should("Get account materials") {
            // given
            stubAuthResponse("/account/materials", "/responses/account/materials.json", apiKey)

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
            stubAuthResponse("/account/minis", "/responses/account/minis.json", apiKey)

            //when 
            val minis = accountClient.getMinis()

            // then
            minis shouldContainExactly listOf(1, 65, 67, 80, 86, 93, 100)
        }

        should("Get unlocked mount skins") {
            // given
            stubAuthResponse("/account/mounts/skins", "/responses/account/mount_skins.json", apiKey)

            // when
            val mountSkins = accountClient.getMountSkins()

            // then
            mountSkins shouldContainExactly listOf(1, 2, 3, 4, 6, 103)
        }

        should("Get unlocked mount types") {
            // given
            stubAuthResponse("/account/mounts/types", "/responses/account/mount_types.json", apiKey)

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
            stubAuthResponse("/account/novelties", "/responses/account/novelties.json", apiKey)

            // when
            val novelties = accountClient.getNovelties()

            // then
            novelties shouldContainExactly listOf(1, 2, 3)
        }

        should("Get outfits") {
            // given
            stubAuthResponse("/account/outfits", "/responses/account/outfits.json", apiKey)

            // when
            val outfits = accountClient.getOutfits()

            // then
            outfits shouldContainExactly listOf(5, 27, 44)
        }

        should("Get pvp heroes") {
            // given
            stubAuthResponse("/account/pvp/heroes", "/responses/account/pvp_heroes.json", apiKey)

            // when
            val pvpHeroes = accountClient.getPvpHeroes()

            // then
            pvpHeroes shouldContainExactly listOf(1, 2, 3)
        }

        should("Get raids") {
            // given
            stubAuthResponse("/account/raids", "/responses/account/raids.json", apiKey)

            // when
            val raids = accountClient.getRaids()

            // then
            raids shouldContainExactly listOf("gorseval", "xera")
        }

        should("Get recipes") {
            // given
            stubAuthResponse("/account/recipes", "/responses/account/recipes.json", apiKey)

            // when
            val recipes = accountClient.getRecipes()

            // then
            recipes shouldContainExactly listOf(104, 105, 11888, 11889)
        }

        should("Get skins") {
            // given
            stubAuthResponse("/account/skins", "/responses/account/skins.json", apiKey)

            // when
            val skins = accountClient.getSkins()

            // then
            skins shouldContainExactly listOf(1, 2, 3)
        }

        should("Get titles") {
            // given
            stubAuthResponse("/account/titles", "/responses/account/titles.json", apiKey)

            // when
            val titles = accountClient.getTitles()

            // then
            titles shouldContainExactly listOf(11, 12, 13, 190, 204, 213)
        }

        should("Get wallet") {
            // given
            stubAuthResponse("/account/wallet", "/responses/account/wallet.json", apiKey)

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
            stubAuthResponse("/account/worldbosses", "/responses/account/worldbosses.json", apiKey)

            // when
            val worldbosses = accountClient.getWorldBosses()

            // then
            worldbosses shouldContainExactly listOf("admiral_taidha_covington")
        }
    }
}