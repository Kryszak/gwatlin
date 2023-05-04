package com.kryszak.gwatlin.api.guild

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

internal class GuildClientTest : BaseWiremockTest() {

    private val guildClient = GWGuildClient()

    init {
        should("Get guild") {
            // given
            val id = "116E0C0E-0035-44A9-BB22-4AE3E23127E5"

            stubResponse("/guild/$id", "/responses/guild/guild.json")

            // then
            val guild = guildClient.getGuild(id)

            // then
            assertSoftly(guild) {
                id shouldBe "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
                name shouldBe "Edit Conflict"
                tag shouldBe "wiki"
                assertSoftly(emblem) {
                    background.shouldNotBeNull()
                    foreground.shouldNotBeNull()
                    flags shouldHaveSize 1
                }
            }
        }

        should("Get background ids") {
            // given
            stubResponse("/emblem/backgrounds", "/responses/guild/background_ids.json")

            // when
            val backgroundIds = guildClient.getBackgroundIds()

            // then
            backgroundIds shouldHaveSize 27
        }

        should("Get backgrounds") {
            // given
            val ids = listOf(1, 2)

            stubResponse("/emblem/backgrounds?ids=1,2", "/responses/guild/backgrounds.json")

            // when
            val backgrounds = guildClient.getBackgrounds(ids)

            // then
            assertSoftly(backgrounds[0]) {
                id shouldBe 1
                layers shouldHaveSize 1
            }
        }

        should("Get foreground ids") {
            // given
            stubResponse("/emblem/foregrounds", "/responses/guild/foreground_ids.json")

            // when
            val foregroundIds = guildClient.getForegroundIds()

            // then
            foregroundIds shouldHaveSize 276
        }

        should("Get foregrounds") {
            // given
            val ids = listOf(1, 2)

            stubResponse("/emblem/foregrounds?ids=1,2", "/responses/guild/foregrounds.json")

            // when
            val foregrounds = guildClient.getForegrounds(ids)

            // then
            assertSoftly(foregrounds[0]) {
                id shouldBe 1
                layers shouldHaveSize 3
            }
        }

        should("Get guild permission ids") {
            // given
            stubResponse("/guild/permissions", "/responses/guild/permission_ids.json")

            // when
            val permissionIds = guildClient.getGuildPermissionIds()

            // then
            permissionIds shouldHaveSize 32
        }

        should("Get guild permissions") {
            // given
            val ids = listOf("ClaimableEditOptions", "Admin", "EditAnthem")
            val lang = ApiLanguage.EN

            stubResponseWithLanguage(
                "/guild/permissions?ids=ClaimableEditOptions,Admin,EditAnthem",
                "/responses/guild/permissions.json",
                lang
            )

            // when
            val permissions = guildClient.getGuildPermissions(ids, lang)

            // then
            assertSoftly(permissions[0]) {
                id shouldBe "ClaimableEditOptions"
                name shouldBe "Edit Claimable Options"
                description shouldBe "Allowed to edit options at guild-owned claimables."
            }
        }

        should("Find guild's id") {
            // given
            val name = "Edit Conflict"

            stubResponse("/guild/search?name=Edit%20Conflict", "/responses/guild/guild_id.json")

            // when
            val guildId = guildClient.findGuildId(name)

            // then
            guildId shouldBe "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
        }

        should("Return empty string if guild id is not found") {
            // given
            val name = "Edit Conflic"

            stubResponse("/guild/search?name=Edit%20Conflic", "/responses/guild/guild_id_not_found.json")

            // when
            val guildId = guildClient.findGuildId(name)

            // then
            guildId shouldBe ""
        }

        should("Get guild upgrade ids") {
            // given
            stubResponse("/guild/upgrades", "/responses/guild/upgrade_ids.json")

            // when
            val upgradeIds = guildClient.getGuildUpgradeIds()

            // then
            upgradeIds shouldHaveSize 827
        }

        should("Get guild upgrades") {
            // given
            val ids = listOf(38, 43)
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/guild/upgrades?ids=38,43", "/responses/guild/upgrades.json", lang)

            // when
            val upgrades = guildClient.getGuildUpgrades(ids, lang)

            // then
            assertSoftly(upgrades[0]) {
                id shouldBe 38
                name shouldBe "Guild Armorer 1"
                description shouldBe "Add a guild armorer to the market, from whom basic guild armor skins can be purchased."
                buildTime shouldBe 0
                icon shouldBe "https://render.guildwars2.com/file/0321165D23EAA60D9831456BEC0095C0EB1D501F/1228499.png"
                type shouldBe "Unlock"
                requiredLevel shouldBe 10
                experience shouldBe 35
                prerequisites shouldHaveSize 1
                assertSoftly(costs[0]) {
                    type shouldBe "Collectible"
                    count shouldBe 200
                    name shouldBe "Guild Favor"
                    itemId shouldBe 70701
                }
            }
        }
    }
}