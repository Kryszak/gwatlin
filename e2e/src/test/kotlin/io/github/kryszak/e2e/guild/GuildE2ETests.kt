package io.github.kryszak.e2e.guild

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.guild.GWGuildClient
import io.kotest.assertions.throwables.shouldNotThrowAny

class GuildE2ETests : BaseE2ESpec() {
    init {
        context("Guild unauthenticated") {
            val client = GWGuildClient()
            expect("Find and fetch guild") {
                val guildId = client.findGuildId("Edit Conflict")
                shouldNotThrowAny { client.getGuild(guildId) }
            }
            expect("Find and fetch backgrounds") {
                val backgroundIds = client.getBackgroundIds()
                shouldNotThrowAny { client.getBackgrounds(backgroundIds) }
            }
            expect("Find and fetch foregrounds") {
                val foregroundIds = client.getForegroundIds().randomElements(100)
                shouldNotThrowAny { client.getForegrounds(foregroundIds) }
            }
            expect("Find and fetch permissions") {
                val permissionIds = client.getGuildPermissionIds()
                shouldNotThrowAny { client.getGuildPermissions(permissionIds) }
            }
            expect("Find and fetch upgrades") {
                val upgradeIds = client.getGuildUpgradeIds().randomElements(100)
                shouldNotThrowAny { client.getGuildUpgrades(upgradeIds) }
            }
        }
    }
}