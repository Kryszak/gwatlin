package com.kryszak.gwatlin.clients.guild

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.guild.model.Guild
import com.kryszak.gwatlin.api.guild.model.emblem.Layer
import com.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import spock.lang.Subject

class GuildClientSpec extends GuildStubs {

    @Subject
    def guildClient = new GuildClient()

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
