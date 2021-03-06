package com.kryszak.gwatlin.api.guild

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.guild.model.Guild
import com.kryszak.gwatlin.api.guild.model.emblem.Layer
import com.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import com.kryszak.gwatlin.api.guild.model.upgrade.GuildUpgrade
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class GuildClientSpec extends WiremockConfig {

    @Subject
    def guildClient = new GWGuildClient()

    def "Should get guild"() {
        given: "Guild id"
        def id = "116E0C0E-0035-44A9-BB22-4AE3E23127E5"

        and: "External api is stubbed"
        stubResponse("/guild/116E0C0E-0035-44A9-BB22-4AE3E23127E5", "/responses/guild/guild.json")

        when: "Requesting guild"
        def guild = guildClient.getGuild(id)

        then: "Retrieved guild matches expected"
        guild == parseGuild()
        verifyAll(guild) {
            id == "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
            name == "Edit Conflict"
            tag == "wiki"
            verifyAll(emblem) {
                background != null
                foreground != null
                flags.size() == 1
            }
        }
    }

    def "Should get background ids"() {
        given: "Expected background ids"
        def ids = parseResponse("/responses/guild/background_ids.json")

        and: "External api is stubbed"
        stubResponse("/emblem/backgrounds", "/responses/guild/background_ids.json")

        when: "Requesting background ids"
        def backgroundIds = guildClient.getBackgroundIds()

        then: "Retrieved list matches expected"
        backgroundIds == ids
    }

    def "Should get backgrounds"() {
        given: "Background ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/emblem/backgrounds?ids=1,2", "/responses/guild/backgrounds.json")

        when: "Requesting backgrounds"
        def backgrounds = guildClient.getBackgrounds(ids)

        then: "Retrieved list matches expected"
        backgrounds == parseBackgrounds()
    }

    def "Should get foreground ids"() {
        given: "Expected list of ids"
        def ids = parseResponse("/responses/guild/foreground_ids.json")

        and: "External api is stubbed"
        stubResponse("/emblem/foregrounds", "/responses/guild/foreground_ids.json")

        when: "Requesting foreground ids"
        def foregroundIds = guildClient.getForegroundIds()

        then: "Retrieved list matches expected"
        foregroundIds == ids
    }

    def "Should get foregrounds"() {
        given: "Foreground ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/emblem/foregrounds?ids=1,2", "/responses/guild/foregrounds.json")

        when: "Requesting foregrounds"
        def foregrounds = guildClient.getForegrounds(ids)

        then: "Retrieved list matches expected"
        foregrounds == parseForegrounds()
        verifyAll(foregrounds.get(0)) {
            id == 1
            layers.size() == 3
        }
    }

    def "Should get guild permission ids"() {
        given: "Expected permission ids"
        def ids = parseResponse("/responses/guild/permission_ids.json")

        and: "External api is stubbed"
        stubResponse("/guild/permissions", "/responses/guild/permission_ids.json")

        when: "Requesting permission ids"
        def permissionIds = guildClient.getGuildPermissionIds()

        then: "Retrieved list matches expected"
        permissionIds == ids
    }

    def "Should get guild permissions"() {
        given: "Permission ids"
        def ids = ["ClaimableEditOptions", "Admin", "EditAnthem"]

        and: "External api is stubbed"
        stubResponse("/guild/permissions?ids=ClaimableEditOptions,Admin,EditAnthem&lang=en", "/responses/guild/permissions.json")

        when: "Requesting guild permissions"
        def permissions = guildClient.getGuildPermissions(ids, "en")

        then: "Retrieved list matches expected"
        permissions == parsePermissions()
        verifyAll(permissions.get(0)) {
            id == "ClaimableEditOptions"
            name == "Edit Claimable Options"
            description == "Allowed to edit options at guild-owned claimables."
        }
    }

    def "Should find guild's id"() {
        given: "Guild name"
        def name = "Edit Conflict"

        and: "External api is stubbed"
        stubResponse("/guild/search?name=Edit%20Conflict", "/responses/guild/guild_id.json")

        when: "Guild id is searched"
        def guildId = guildClient.findGuildId(name)

        then: "Found id matches expected"
        guildId == "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
    }

    def "Should return empty string if guild id is not found"() {
        given: "Non existing guild name"
        def name = "Edit Conflic"

        and: "External api is stubbed"
        stubResponse("/guild/search?name=Edit%20Conflic", "/responses/guild/guild_id_not_found.json")

        when: "Guild id is searched"
        def guildId = guildClient.findGuildId(name)

        then: "Empty string is returned"
        guildId == ""
    }

    def "Should get guild upgrade ids"() {
        given: "Expected upgrade ids"
        def ids = parseResponse("/responses/guild/upgrade_ids.json")

        and: "External api is stubbed"
        stubResponse("/guild/upgrades", "/responses/guild/upgrade_ids.json")

        when: "Upgrade ids are requested"
        def upgradeIds = guildClient.getGuildUpgradeIds()

        then: "Retrieved list matches expected"
        upgradeIds == ids
    }

    def "Should get guild upgrades"() {
        given: "Guild upgrade ids"
        def ids = [38, 43]

        and: "External api is stubbed"
        stubResponse("/guild/upgrades?ids=38,43&lang=en", "/responses/guild/upgrades.json")

        when: "Requesting guild upgrades"
        def upgrades = guildClient.getGuildUpgrades(ids, "en")

        then: "Retrieved list matches expected"
        upgrades == parseUpgrades()
        verifyAll(upgrades.get(0)) {
            id == 38
            name == "Guild Armorer 1"
            description == "Add a guild armorer to the market, from whom basic guild armor skins can be purchased."
            buildTime == 0
            icon == "https://render.guildwars2.com/file/0321165D23EAA60D9831456BEC0095C0EB1D501F/1228499.png"
            type == "Unlock"
            requiredLevel == 10
            experience == 35
            prerequisites.size() == 1
            verifyAll(costs.get(0)) {
                type == "Collectible"
                count == 200
                name == "Guild Favor"
                itemId == 70701
            }
        }
    }

    private List<GuildUpgrade> parseUpgrades() {
        gson.fromJson(parseResponseText("/responses/guild/upgrades.json"),
                new TypeToken<List<GuildUpgrade>>() {}.getType())
    }

    private List<GuildPermission> parsePermissions() {
        gson.fromJson(parseResponseText("/responses/guild/permissions.json"),
                new TypeToken<List<GuildPermission>>() {}.getType())
    }

    private List<Layer> parseForegrounds() {
        gson.fromJson(parseResponseText("/responses/guild/foregrounds.json"),
                new TypeToken<List<Layer>>() {}.getType())
    }

    private List<Layer> parseBackgrounds() {
        gson.fromJson(parseResponseText("/responses/guild/backgrounds.json"),
                new TypeToken<List<Layer>>() {}.getType())
    }

    private Guild parseGuild() {
        gson.fromJson(parseResponseText("/responses/guild/guild.json"), Guild)
    }
}
