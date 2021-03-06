package com.kryszak.gwatlin.api.miscellaneous

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class MiscellaneousClientSpec extends WiremockConfig {

    @Subject
    def miscellaneousClient = new GWMiscellaneousClient()

    def "Should get build id"() {
        given: "External api is stubbed"
        stubResponse("/build", "/responses/miscellaneous/build_id.json")

        when: "Requesting build id"
        def buildId = miscellaneousClient.getBuildId()

        then: "Retrieved id matches expected"
        buildId == 45075
    }

    def "Should get icons"() {
        given: "External api is stubbed"
        stubResponse("/files?ids=all", "/responses/miscellaneous/icons.json")

        when: "Requesting icons"
        def icons = miscellaneousClient.getIcons()

        then: "Retrieved list matches expected"
        verifyAll(icons.get(0)) {
            id == "map_complete"
            icon == "https://render.guildwars2.com/file/5A4E663071250EC72668C09E3C082E595A380BF7/528724.png"
        }
    }

    def "Should get quaggans"() {
        given: "External api is stubbed"
        stubResponse("/quaggans?ids=all", "/responses/miscellaneous/quaggans.json")

        when: "Requesting quaggans"
        def quaggans = miscellaneousClient.getQuaggans()

        then: "Retrieved list matches expected"
        quaggans.size() == 35
    }

    def "Should get dye colors"() {
        given: "External api is stubbed"
        stubResponse("/colors?ids=all&lang=en", "/responses/miscellaneous/dye_colors.json")

        when: "Requesting dye colors"
        def colors = miscellaneousClient.getDyeColors("en")

        then: "Retrieved list matches expected"
        verifyAll(colors.get(1)) {
            id == 2
            name == "Black"
            baseRgb == [128, 26, 26]
            verifyAll(cloth) {
                brightness == -13
                contrast == 1
                hue == 275
                saturation == 0.0234375d
                lightness == 1.09375d
                rgb == [37, 35, 38]
            }
            leather != null
            metal != null
            fur != null
            item == 20358
            categories == ["Gray", "Metal", "Rare"]
        }
    }

    def "Should get currencies"() {
        given: "External api is stubbed"
        stubResponse("/currencies?ids=all&lang=en", "/responses/miscellaneous/currencies.json")

        when: "Requesting currencies"
        def currencies = miscellaneousClient.getCurrencies("en")

        then: "Retrieved currencies match expected"
        verifyAll(currencies.get(0)) {
            id == 1
            name == "Coin"
            description == "The primary currency of Tyria. Spent at vendors throughout the world."
            order == 101
            icon == "https://render.guildwars2.com/file/98457F504BA2FAC8457F532C4B30EDC23929ACF9/619316.png"
        }
    }

    def "Should get dungeons"() {
        given: "External api is stubbed"
        stubResponse("/dungeons?ids=all&lang=en", "/responses/miscellaneous/dungeons.json")

        when: "Requesting dungeons"
        def dungeons = miscellaneousClient.getDungeons("en")

        then: "Retrieved dungeons match expected"
        verifyAll(dungeons.get(0)) {
            id == "ascalonian_catacombs"
            verifyAll(paths.get(0)) {
                id == "ac_story"
                type == "Story"
            }
        }
    }

    def "Should get minis"() {
        given: "External api is stubbed"
        stubResponse("/minis?ids=all&lang=en", "/responses/miscellaneous/minis.json")

        when: "Requesting minis"
        def minis = miscellaneousClient.getMinis("en")

        then: "Retrieved minis matches expected"
        verifyAll(minis.get(0)) {
            id == 1
            name == "Mini Rytlock"
            icon == "https://render.guildwars2.com/file/795ED1B945A29EC3E3066797DF57FFB25ABAA631/340551.png"
            order == 1
            itemId == 21047
        }
    }

    def "Should get novelties"() {
        given: "External api is stubbed"
        stubResponse("/novelties?ids=all&lang=en", "/responses/miscellaneous/novelties.json")

        when: "Requesting novelties"
        def novelties = miscellaneousClient.getNovelties("en")

        then: "Retrieved novelties match expected"
        verifyAll(novelties.get(0)) {
            id == 1
            name == "Embellished Kite"
            description == "<c=@abilitytype>Held Item.</c> Equip a bundle for decoration or to use noncombat skills."
            icon == "https://render.guildwars2.com/file/7B043D640ED57517051D5FC038D7CDDDE5F82933/2015154.png"
            slot == "HeldItem"
            unlockItem == [88124]
        }
    }

    def "Should get raids"() {
        given: "External api is stubbed"
        stubResponse("/raids?ids=all&lang=en", "/responses/miscellaneous/raids.json")

        when: "Requesting raids"
        def raids = miscellaneousClient.getRaids("en")

        then: "Retrieved raids match expected"
        verifyAll(raids.get(0)) {
            id == "forsaken_thicket"
            verifyAll(wings.get(0)) {
                id == "spirit_vale"
                verifyAll(events.get(0)) {
                    id == "vale_guardian"
                    type == "Boss"
                }
            }
        }
    }

    def "Should get titles"() {
        given: "External api is stubbed"
        stubResponse("/titles?ids=all&lang=en", "/responses/miscellaneous/titles.json")

        when: "Requesting titles"
        def titles = miscellaneousClient.getTitles("en")

        then: "Retrieved titles matches expected"
        verifyAll(titles.get(0)) {
            id == 1
            name == "Traveler"
            achievement == 111
            achievements == [111]
        }
    }

    def "Should get worlds"() {
        given: "External api is stubbed"
        stubResponse("/worlds?ids=all&lang=en", "/responses/miscellaneous/worlds.json")

        when: "Requesting worlds"
        def worlds = miscellaneousClient.getWorlds("en")

        then: "Retrieved worlds match expected"
        verifyAll(worlds.get(0)) {
            id == 1001
            name == "Anvil Rock"
            population == "High"
        }
    }
}
