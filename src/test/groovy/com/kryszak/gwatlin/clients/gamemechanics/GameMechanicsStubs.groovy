package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.notFound
import static com.github.tomakehurst.wiremock.client.WireMock.okJson
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

class GameMechanicsStubs extends WiremockConfig {

    def stubMasteriesIdsResponse() {
        stubFor(
                get(urlEqualTo("/masteries"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/masteries_ids.json")))
        )
    }

    def stubMasteryResponse() {
        stubFor(
                get(urlEqualTo("/masteries/1?lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mastery.json")))
        )
    }

    def stubMasteryFrenchResponse() {
        stubFor(
                get(urlEqualTo("/masteries/1?lang=fr"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mastery_fr.json")))
        )
    }

    def stubMasteryErrorResponse() {
        stubFor(
                get(urlEqualTo("/masteries/40?lang=en"))
                        .willReturn(notFound()
                                .withBody(parseResponseText("/responses/gamemechanics/mastery_error.json")))
        )
    }

    def stubMasteriesResponse() {
        stubFor(
                get(urlEqualTo("/masteries?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/masteries.json")))
        )
    }

    def stubAllMasteriesResponse() {
        stubFor(
                get(urlEqualTo("/masteries?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/masteries_all.json")))
        )
    }

    def stubMountSkinIdsResponse() {
        stubFor(
                get(urlEqualTo("/mounts/skins"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_skin_ids.json")))
        )
    }

    def stubMountSkinsResponse() {
        stubFor(
                get(urlEqualTo("/mounts/skins?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_skins.json")))
        )
    }

    def stubMountSkinErrorResponse() {
        stubFor(
                get(urlEqualTo("/mounts/skins?ids=1000&lang=en"))
                        .willReturn(notFound().withBody(parseResponseText(("/responses/gamemechanics/mount_skins_error.json"))))
        )
    }

    def stubAllMountSkinsResponse() {
        stubFor(
                get(urlEqualTo("/mounts/skins?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_skins_all.json")))
        )
    }

    def stubMountTypesIdsResponse() {
        stubFor(
                get(urlEqualTo("/mounts/types"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_types_ids.json")))
        )
    }

    def stubMountTypesResponse() {
        stubFor(
                get(urlEqualTo("/mounts/types?ids=griffon,jackal&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_types.json")))
        )
    }

    def stubAllMountTypeResponse() {
        stubFor(
                get(urlEqualTo("/mounts/types?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/mount_types_all.json")))
        )
    }

    def stubMountTypeErrorResponse() {
        stubFor(
                get(urlEqualTo("/mounts/types?ids=i_do_not_exist&lang=en"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/gamemechanics/mount_type_error.json")))
        )
    }

    def stubOutfitIdsResponse() {
        stubFor(
                get(urlEqualTo("/outfits"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/outfit_ids.json")))
        )
    }

    def stubOutfitsResponse() {
        stubFor(
                get(urlEqualTo("/outfits?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/outfits.json")))
        )
    }

    def stubOutfitErrorResponse() {
        stubFor(
                get(urlEqualTo("/outfits?ids=1000&lang=en"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/gamemechanics/outfit_error.json")))
        )
    }

    def stubAllOutfitsResponse() {
        stubFor(
                get(urlEqualTo("/outfits?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/outfits_all.json")))
        )
    }

    def stubPetIdsResponse() {
        stubFor(
                get(urlEqualTo("/pets"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/pet_ids.json")))
        )
    }

    def stubPetsResponse() {
        stubFor(
                get(urlEqualTo("/pets?ids=1,2&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/pets.json")))
        )
    }

    def stubAllPetsResponse() {
        stubFor(
                get(urlEqualTo("/pets?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/pets_all.json")))
        )
    }
}
