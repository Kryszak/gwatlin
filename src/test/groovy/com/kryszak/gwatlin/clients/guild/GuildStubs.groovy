package com.kryszak.gwatlin.clients.guild

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.*

class GuildStubs extends WiremockConfig {

    def stubGuildResponse() {
        stubFor(
                get(urlEqualTo("/guild/116E0C0E-0035-44A9-BB22-4AE3E23127E5"))
                        .willReturn(okJson(parseResponseText("/responses/guild/guild.json")))
        )
    }

    def stubBackgroundIdsResponse() {
        stubFor(
                get(urlEqualTo("/emblem/backgrounds"))
                        .willReturn(okJson(parseResponseText("/responses/guild/background_ids.json")))
        )
    }

    def stubBackgroundResponse() {
        stubFor(
                get(urlEqualTo("/emblem/backgrounds?ids=1,2"))
                        .willReturn(okJson(parseResponseText("/responses/guild/backgrounds.json")))
        )
    }

    def stubForegroundIdsResponse() {
        stubFor(
                get(urlEqualTo("/emblem/foregrounds"))
                        .willReturn(okJson(parseResponseText("/responses/guild/foreground_ids.json")))
        )
    }

    def stubForegroundResponse() {
        stubFor(
                get(urlEqualTo("/emblem/foregrounds?ids=1,2"))
                        .willReturn(okJson(parseResponseText("/responses/guild/foregrounds.json")))
        )
    }

    def stubGuildPermissionIdsResponse() {
        stubFor(
                get(urlEqualTo("/guild/permissions"))
                        .willReturn(okJson(parseResponseText("/responses/guild/permission_ids.json")))
        )
    }

    def stubGuildPermissionsResponse() {
        stubFor(
                get(urlEqualTo("/guild/permissions?ids=ClaimableEditOptions,Admin,EditAnthem&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/guild/permissions.json")))
        )
    }

    def stubGuildIdResponse() {
        stubFor(
                get(urlEqualTo("/guild/search?name=Edit%20Conflict"))
                        .willReturn(okJson(parseResponseText("/responses/guild/guild_id.json")))
        )
    }

    def stubGuildIdNotFoundResponse() {
        stubFor(
                get(urlEqualTo("/guild/search?name=Edit%20Conflic"))
                        .willReturn(okJson(parseResponseText("/responses/guild/guild_id_not_found.json")))
        )
    }

    def stubUpgradeIdsResponse() {
        stubFor(
                get(urlEqualTo("/guild/upgrades"))
                        .willReturn(okJson(parseResponseText("/responses/guild/upgrade_ids.json")))
        )
    }

    def stubUpgradesResponse() {
        stubFor(
                get(urlEqualTo("/guild/upgrades?ids=38,43&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/guild/upgrades.json")))
        )
    }

    def stubGuildLogFullResponse() {
        stubFor(
                get(urlEqualTo("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log"))
                        .withHeader("Authorization", equalTo("Bearer 1234"))
                        .willReturn(okJson(parseResponseText("/responses/guild/guild_log.json")))
        )
    }

    def stubGuildLogSinceResponse() {
        stubFor(
                get(urlEqualTo("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log?since=1285"))
                        .withHeader("Authorization", equalTo("Bearer 1234"))
                        .willReturn(okJson(parseResponseText("/responses/guild/guild_log_since.json")))
        )
    }

    def stubGuildLogUnauthenticatedResponse() {
        stubFor(
                get(urlEqualTo("/guild/4BBB52AA-D768-4FC6-8EDE-C299F2822F0F/log"))
                        .withHeader("Authorization", equalTo("Bearer 123"))
                        .willReturn(unauthorized().withBody(parseResponseText("/responses/guild/guild_log_unauthenticated.json")))
        )
    }
}
