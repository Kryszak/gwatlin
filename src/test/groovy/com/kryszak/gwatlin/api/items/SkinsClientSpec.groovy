package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class SkinsClientSpec extends WiremockTest {

    @Subject
    def skinsClient = new GWSkinsClient()

    def "Should get skin ids"() {
        given: "Expected skin ids list"
        def ids = parseResponse("/responses/items/skin_ids.json")

        and: "External api is stubbed"
        stubResponse("/skins", "/responses/items/skin_ids.json")

        when: "Requesting skin ids list"
        def skinIds = skinsClient.getSkinIds()

        then: "Retrieved list matches expected"
        skinIds == ids
    }

    def "Should get skin"() {
        given: "Skin id"
        def id = 10

        and: "External api is stubbed"
        stubResponse("/skins?ids=10&lang=en", "/responses/items/skin.json")

        when: "Requesting skin"
        def skins = skinsClient.getSkins([id], "en")

        then: "Retrieved skin matches expected"
        verifyAll(skins.get(0)) {
            id == 10
            type == "Armor"
            flags.size() == 1
            restrictions == []
            rarity == "Basic"
            icon == "https://render.guildwars2.com/file/1920ACA302E656B60C38605521760351F147809D/61088.png"
        }
    }
}
