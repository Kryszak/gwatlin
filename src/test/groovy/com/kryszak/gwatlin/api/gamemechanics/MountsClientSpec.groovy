package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class MountsClientSpec extends WiremockConfig {

    @Subject
    def mountsClient = new GWMountsClient()

    def "Should get mount skins ids"() {
        given: "External api is stubbed"
        stubResponse("/mounts/skins", "/responses/gamemechanics/mount_skin_ids.json")

        when: "Retrieving mount skin ids list"
        def idsList = mountsClient.getMountSkinsIds()

        then: "Retrieved list matches expected"
        idsList.size() == 172
    }

    def "Should get list of mount skins"() {
        given: "List of ids"
        def ids = [1, 2]

        and: "External api is stubbed"
        stubResponse("/mounts/skins?ids=1,2&lang=en", "/responses/gamemechanics/mount_skins.json")

        when: "Mount skins are requested"
        def mountSkins = mountsClient.getMountSkins(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(mountSkins.get(0)) {
            id == 1
            name == "Raptor"
            icon == "https://render.guildwars2.com/file/2F4AAA52F573C5425BFCD7525FB70C9E6DCAD791/1766903.png"
            mount == "raptor"
            verifyAll(dyeSlots.get(0)) {
                colorId == 19
                material == "leather"
            }
        }
    }

    def "Should throw exception on non existing mount skin id"() {
        given: "Id of non existing mount skin"
        def id = 1000

        and: "External api is stubbed"
        stubNotFoundResponse("/mounts/skins?ids=1000&lang=en", "/responses/gamemechanics/mount_skins_error.json")

        when: "Non existing mount skin is requested"
        mountsClient.getMountSkins([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get all mount skins"() {
        given: "External api is stubbed"
        stubResponse("/mounts/skins?ids=all&lang=en", "/responses/gamemechanics/mount_skins_all.json")

        when: "All mount skins are requested"
        def mountSkins = mountsClient.getAllMountSkins("en")

        then: "Retrieved list matches expected"
        mountSkins.size() == 172
    }

    def "Should get mount types ids"() {
        given: "External api is stubbed"
        stubResponse("/mounts/types", "/responses/gamemechanics/mount_types_ids.json")

        when: "Mount types ids are requested"
        def typesIds = mountsClient.getMountTypesIds()

        then: "Retrieved list matches expected"
        typesIds.size() == 8
    }

    def "Should get mount types"() {
        given: "Mount ids"
        def ids = ["griffon", "jackal"]

        and: "External api is stubbed"
        stubResponse("/mounts/types?ids=griffon,jackal&lang=en", "/responses/gamemechanics/mount_types.json")

        when: "Requesting mount types"
        def mountTypes = mountsClient.getMountTypes(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(mountTypes.get(0)) {
            id == "griffon"
            name == "Griffon"
            defaultSkin == 4
            skins.size() == 29
            skills.size() == 1
            verifyAll(skills.get(0)) {
                id == 40576
                slot == "Weapon_1"
            }
        }
    }

    def "Should get all mount types"() {
        given: "External api is stubbed"
        stubResponse("/mounts/types?ids=all&lang=en", "/responses/gamemechanics/mount_types_all.json")

        when: "Requesting all mount types"
        def mountTypes = mountsClient.getAllMountTypes("en")

        then: "Retrieved list matches expected"
        mountTypes.size() == 8
    }

    def "Should throw exception on non existing mount type id"() {
        given: "Id of non existing mount type"
        def id = "i_do_not_exist"

        and: "External api is stubbed"
        stubNotFoundResponse("/mounts/types?ids=i_do_not_exist&lang=en", "/responses/gamemechanics/mount_type_error.json")

        when: "Requesting non existing mount type"
        mountsClient.getMountTypes([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }
}
