package com.kryszak.gwatlin.clients.guild

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

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
}
