package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class MountsClientSpec extends WiremockTest {

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

        and: "language"
        def lang = "en"

        and: "External api is stubbed"
        stubResponseWithLanguage("/mounts/skins?ids=1,2", "/responses/gamemechanics/mount_skins.json", lang)

        when: "Mount skins are requested"
        def mountSkins = mountsClient.getMountSkins(ids, lang)

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
        stubNotFoundResponse("/mounts/skins?ids=1000", "/responses/gamemechanics/mount_skins_error.json")

        when: "Non existing mount skin is requested"
        mountsClient.getMountSkins([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get all mount skins"() {
        given: "language"
        def lang = "en"

        and: "External api is stubbed"
        stubResponseWithLanguage("/mounts/skins?ids=all", "/responses/gamemechanics/mount_skins_all.json", lang)

        when: "All mount skins are requested"
        def mountSkins = mountsClient.getAllMountSkins(lang)

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

        and: "language"
        def lang = "en"

        and: "External api is stubbed"
        stubResponseWithLanguage("/mounts/types?ids=griffon,jackal", "/responses/gamemechanics/mount_types.json", lang)

        when: "Requesting mount types"
        def mountTypes = mountsClient.getMountTypes(ids, lang)

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
        given: "language"
        def lang = "en"

        and: "External api is stubbed"
        stubResponseWithLanguage("/mounts/types?ids=all", "/responses/gamemechanics/mount_types_all.json", lang)

        when: "Requesting all mount types"
        def mountTypes = mountsClient.getAllMountTypes(lang)

        then: "Retrieved list matches expected"
        mountTypes.size() == 8
    }

    def "Should throw exception on non existing mount type id"() {
        given: "Id of non existing mount type"
        def id = "i_do_not_exist"

        and: "External api is stubbed"
        stubNotFoundResponse("/mounts/types?ids=i_do_not_exist", "/responses/gamemechanics/mount_type_error.json")

        when: "Requesting non existing mount type"
        mountsClient.getMountTypes([id], "en")

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }
}
