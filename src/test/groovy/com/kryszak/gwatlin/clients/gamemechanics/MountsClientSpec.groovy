package com.kryszak.gwatlin.clients.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.achievement.model.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.mount.MountSkin
import spock.lang.Subject

class MountsClientSpec extends GameMechanicsStubs {

    @Subject
    def mountsClient = new MountsClient()

    def "Should get mount skins ids"() {
        given: "Expected list of ids"
        def ids = parseResponse("/responses/gamemechanics/mount_skin_ids.json")

        and: "External api is stubbed"
        stubMountSkinIdsResponse()

        when: "Retrieving mount skin ids list"
        def idsList = mountsClient.getMountSkinsIds()

        then: "Retrieved list matches expected"
        idsList == ids
    }

    def "Should get list of mount skins"() {
        given: "List of ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubMountSkinsResponse()

        when: "Mount skins are requested"
        def mountSkins = mountsClient.getMountSkins(ids, "en")

        then: "Retrieved list matches expected"
        mountSkins == parseMountSkins("mount_skins.json")
    }

    def "Should throw exception on non existing mount skin id"() {
        given: "Id of non existing mount skin"
        def id = 1000

        and: "External api is stubbed"
        stubMountSkinErrorResponse()

        when: "Non existing mount skin is requested"
        mountsClient.getMountSkins([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get all mount skins"() {
        given: "External api is stubbed"
        stubAllMountSkinsResponse()

        when: "All mount skins are requested"
        def mountSkins = mountsClient.getAllMountSkins("en")

        then: "Retrieved list matches expected"
        mountSkins == parseMountSkins("mount_skins_all.json")
    }

    private List<MountSkin> parseMountSkins(String file) {
        gson.fromJson(parseResponseText("/responses/gamemechanics/$file"),
                new TypeToken<List<MountSkin>>() {}.getType())
    }
}
