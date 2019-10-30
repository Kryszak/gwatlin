package com.kryszak.gwatlin.api.guild

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.guild.model.Guild
import com.kryszak.gwatlin.api.guild.model.emblem.Layer
import com.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import com.kryszak.gwatlin.api.guild.model.upgrade.GuildUpgrade
import spock.lang.Subject

class GuildClientSpec extends GuildStubs {

    @Subject
    def guildClient = new GWGuildClient()

    def "Should get guild"() {
        given: "Guild id"
        def id = "116E0C0E-0035-44A9-BB22-4AE3E23127E5"

        and: "External api is stubbed"
        stubGuildResponse()

        when: "Requesting guild"
        def guild = guildClient.getGuild(id)

        then: "Retrieved guild matches expected"
        guild == parseGuild()
    }

    def "Should get background ids"() {
        given: "Expected background ids"
        def ids = parseResponse("/responses/guild/background_ids.json")

        and: "External api is stubbed"
        stubBackgroundIdsResponse()

        when: "Requesting background ids"
        def backgroundIds = guildClient.getBackgroundIds()

        then: "Retrieved list matches expected"
        backgroundIds == ids
    }

    def "Should get backgrounds"() {
        given: "Background ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubBackgroundResponse()

        when: "Requesting backgrounds"
        def backgrounds = guildClient.getBackgrounds(ids)

        then: "Retrieved list matches expected"
        backgrounds == parseBackgrounds()
    }

    def "Should get foreground ids"() {
        given: "Expected list of ids"
        def ids = parseResponse("/responses/guild/foreground_ids.json")

        and: "External api is stubbed"
        stubForegroundIdsResponse()

        when: "Requesting foreground ids"
        def foregroundIds = guildClient.getForegroundIds()

        then: "Retrieved list matches expected"
        foregroundIds == ids
    }

    def "Should get foregrounds"() {
        given: "Foreground ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubForegroundResponse()

        when: "Requesting foregrounds"
        def foregrounds = guildClient.getForegrounds(ids)

        then: "Retrieved list matches expected"
        foregrounds == parseForegrounds()
    }

    def "Should get guild permission ids"() {
        given: "Expected permission ids"
        def ids = parseResponse("/responses/guild/permission_ids.json")

        and: "External api is stubbed"
        stubGuildPermissionIdsResponse()

        when: "Requesting permission ids"
        def permissionIds = guildClient.getGuildPermissionIds()

        then: "Retrieved list matches expected"
        permissionIds == ids
    }

    def "Should get guild permissions"() {
        given: "Permission ids"
        def ids = ["ClaimableEditOptions", "Admin", "EditAnthem"]

        and: "External api is stubbed"
        stubGuildPermissionsResponse()

        when: "Requesting guild permissions"
        def permissions = guildClient.getGuildPermissions(ids, "en")

        then: "Retrieved list matches expected"
        permissions == parsePermissions()
    }

    def "Should find guild's id"() {
        given: "Guild name"
        def name = "Edit Conflict"

        and: "External api is stubbed"
        stubGuildIdResponse()

        when: "Guild id is searched"
        def guildId = guildClient.findGuildId(name)

        then: "Found id matches expected"
        guildId == "116E0C0E-0035-44A9-BB22-4AE3E23127E5"
    }

    def "Should return empty string if guild id is not found"() {
        given: "Non existing guild name"
        def name = "Edit Conflic"

        and: "External api is stubbed"
        stubGuildIdNotFoundResponse()

        when: "Guild id is searched"
        def guildId = guildClient.findGuildId(name)

        then: "Empty string is returned"
        guildId == ""
    }

    def "Should get guild upgrade ids"() {
        given: "Expected upgrade ids"
        def ids = parseResponse("/responses/guild/upgrade_ids.json")

        and: "External api is stubbed"
        stubUpgradeIdsResponse()

        when: "Upgrade ids are requested"
        def upgradeIds = guildClient.getGuildUpgradeIds()

        then: "Retrieved list matches expected"
        upgradeIds == ids
    }

    def "Should get guild upgrades"() {
        given: "Guild upgrade ids"
        def ids = [38, 43]

        and: "External api is stubbed"
        stubUpgradesResponse()

        when: "Requesting guild upgrades"
        def upgrades = guildClient.getGuildUpgrades(ids, "en")

        then: "Retrieved list matches expected"
        upgrades == parseUpgrades()
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
