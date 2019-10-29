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

    def stubProfessionIdsResponse() {
        stubFor(
                get(urlEqualTo("/professions"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/profession_ids.json")))
        )
    }

    def stubProfessionsResponse() {
        stubFor(
                get(urlEqualTo("/professions?ids=Engineer,Warrior&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/professions.json")))
        )
    }

    def stubAllProfessionsResponse() {
        stubFor(
                get(urlEqualTo("/professions?ids=all&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/professions_all.json")))
        )
    }

    def stubProfessionErrorResponse() {
        stubFor(
                get(urlEqualTo("/professions?ids=asdf&lang=en"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/gamemechanics/professions_error.json")))
        )
    }

    def stubRaceIdsResponse() {
        stubFor(
                get(urlEqualTo("/races"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/race_ids.json")))
        )
    }

    def stubRaceResponse() {
        stubFor(
                get(urlEqualTo("/races/Asura"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/race.json")))
        )
    }

    def stubRaceErrorResponse() {
        stubFor(
                get(urlEqualTo("/races/nobody"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/gamemechanics/race_error.json")))
        )
    }

    def stubSpecializationIdsResponse() {
        stubFor(
                get(urlEqualTo("/specializations"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/specialization_ids.json")))
        )
    }

    def stubSpecializationResponse() {
        stubFor(
                get(urlEqualTo("/specializations/1?lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/specialization.json")))
        )
    }

    def stubSpecializationErrorResponse() {
        stubFor(
                get(urlEqualTo("/specializations/100?lang=en"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/gamemechanics/specialization_error.json")))
        )
    }

    def stubSkillIdsResponse() {
        stubFor(
                get(urlEqualTo("/skills"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/skill_ids.json")))
        )
    }

    def stubSkillsResponse() {
        stubFor(
                get(urlEqualTo("/skills?ids=1110,1115&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/skills.json")))
        )
    }

    def stubTraitIdsResponse() {
        stubFor(
                get(urlEqualTo("/traits"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/trait_ids.json")))
        )
    }

    def stubTraitsResponse() {
        stubFor(
                get(urlEqualTo("/traits?ids=214,221&lang=en"))
                        .willReturn(okJson(parseResponseText("/responses/gamemechanics/traits.json")))
        )
    }
}
