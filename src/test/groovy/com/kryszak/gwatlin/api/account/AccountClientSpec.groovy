package com.kryszak.gwatlin.api.account

import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class AccountClientSpec extends WiremockTest {

    def API_KEY = "1234"

    @Subject
    def accountClient = new GWAccountClient(API_KEY)

    def "Should get account details"() {
        given: "External api is stubbed"
        stubAuthResponse("/account", "/responses/account/account.json", API_KEY)

        when: "Requesting account details"
        def account = accountClient.getAccountDetails()

        then: "Retrieved details matches expected"
        verifyAll(account) {
            id == "C19467C6-F5AD-E211-8756-78E7D1936222"
            name == 'Account.1234'
            age == 22911780
            world == 1004
            guilds.size() == 5
            guildLeader.size() == 1
            created == "2013-04-25T22:09:00Z"
            access.size() == 3
            commander
            fractalLevel == 100
            dailyAp == 7659
            monthlyAp == 1129
            wvwRank == 514
        }
    }

    def "Should get account achievements"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/achievements", "/responses/account/achievements.json", API_KEY)

        when: "Requesting account achievements"
        def achievements = accountClient.getAccountAchievements()

        then: "Retrieved info matches expectes"
        verifyAll(achievements.get(2)) {
            id == 1653
            bits.size() == 4
            current == 4
            max == 30
            !done
        }
    }

    def "Should get account vault"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/bank", "/responses/account/bank.json", API_KEY)

        when: "Requesting account vault"
        def vault = accountClient.getAccountVault()

        then: "Retrieved vault matches expected"
        vault.size() == 6
        verifyAll(vault.get(3)) {
            id == 46760
            count == 1
            upgrades.size() == 1
            infusions.size() == 1
        }
    }

    def "Should get account daily crafting"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/dailycrafting", "/responses/account/dailycrafting.json", API_KEY)

        when: "Requesting account daily crafting"
        def dailyCrafting = accountClient.getAccountDailyCrafting()

        then: "Retrieved list matches expected"
        dailyCrafting == [
                "lump_of_mithrilium",
                "spool_of_silk_weaving_thread",
                "spool_of_thick_elonian_cord"
        ]
    }

    def "Should get done daily dungeons"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/dungeons", "/responses/account/dungeons.json", API_KEY)

        when: "Requesting list of completed daily dungeons"
        def dungeons = accountClient.getCompletedDailyDungeons()

        then: "Retrieved list matches expected"
        dungeons == [
                "hodgins",
                "seraph",
                "seer"
        ]
    }

    def "Should get unlocked dyes"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/dyes", "/responses/account/dyes.json", API_KEY)

        when: "Requesting list of unlocked dyes"
        def dyes = accountClient.getUnlockedDyes()

        then: "Retrieved list matches expected"
        dyes == [3, 4, 5, 6]
    }

    def "Should get account finishers"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/finishers", "/responses/account/finishers.json", API_KEY)

        when: "Requesting account finishers"
        def finishers = accountClient.getFinishers()

        then: "Retrieved finishers match expected"
        verifyAll(finishers.get(1)) {
            id == 15
            !permanent
            quantity == 5
        }
    }

    def "Should get account gliders"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/gliders", "/responses/account/gliders.json", API_KEY)

        when: "Requesting account gliders"
        def gliders = accountClient.getGliders()

        then: "Retrieved list matches expected"
        gliders == [1, 2, 3]
    }

    def "Should get account cats"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/home/cats", "/responses/account/cats.json", API_KEY)

        when: "Requesting account cats"
        def cats = accountClient.getCats()

        then: "Retrieved list matches expected"
        verifyAll(cats.get(0)) {
            id == 2
            hint == "grilled_chicken"
        }
    }

    def "Should get unlocked home instance nodes"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/home/nodes", "/responses/account/nodes.json", API_KEY)

        when: "Requesting account home instance nodes"
        def nodes = accountClient.getNodes()

        then: "Retrieved list matches expected"
        nodes == [
                "quartz_node",
                "krait_obelisk",
                "sprocket_generator",
                "wintersday_tree",
                "bandit_chest",
                "aurilium_node",
                "exalted_chest",
                "airship_cargo",
                "crystallized_supply_cache"
        ]
    }

    def "Should get account inventory"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/inventory", "/responses/account/inventory.json", API_KEY)

        when: "Requesting account inventory"
        def inventory = accountClient.getInventory()

        then: "Retrieved inventory matches expected"
        verifyAll(inventory.get(0)) {
            id == 44602
            count == 1
            binding == "Account"
        }
    }

    def "Should get account luck"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/luck", "/responses/account/luck.json", API_KEY)

        when: "Requesting account luck"
        def luck = accountClient.getLuck()

        then: "Retrieved luck matches expected"
        verifyAll(luck) {
            id == "luck"
            value == 4295449
        }
    }

    def "Should get account mail carriers"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/mailcarriers", "/responses/account/mailcarriers.json", API_KEY)

        when: "Requesting account mail carriers"
        def mailcarriers = accountClient.getMailCarriers()

        then: "Retrieved list matches expected"
        mailcarriers == [1, 2, 3]
    }

    def "Should get map chests"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/mapchests", "/responses/account/mapchests.json", API_KEY)

        when: "Requesting account map chests"
        def mapchests = accountClient.getMapChests()

        then: "Retrieved list matches expected"
        mapchests == ["verdant_brink_heros_choice_chest"]
    }

    def "Should get account masteries"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/masteries", "/responses/account/masteries.json", API_KEY)

        when: "Requesting account masteries"
        def masteries = accountClient.getMasteries()

        then: "Retrieved masteries matches expected"
        verifyAll(masteries.get(0)) {
            id == 1
            level == 4
        }
    }

    def "Should get account mastery details"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/mastery/points", "/responses/account/mastery_points.json", API_KEY)

        when: "Requesting mastery details"
        def masteryDetails = accountClient.getMasteryDetails()

        then: "Retrieved details matches expected"
        verifyAll(masteryDetails) {
            unlocked.size() == 2
            verifyAll(totals.get(0)) {
                region == "Tyria"
                spent == 49
                earned == 63
            }
        }
    }

    def "Should get account materials"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/materials", "/responses/account/materials.json", API_KEY)

        when: "Requesting account materials"
        def materials = accountClient.getMaterials()

        then: "Retrieved materials matches expected"
        verifyAll(materials.get(0)) {
            id == 12134
            category == 5
            count == 64
        }
    }

    def "Should get account minis"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/minis", "/responses/account/minis.json", API_KEY)

        when: "Requesting account minis"
        def minis = accountClient.getMinis()

        then: "Retrieved list matches expected"
        minis == [1, 65, 67, 80, 86, 93, 100]
    }

    def "Should get unlocked mount skins"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/mounts/skins", "/responses/account/mount_skins.json", API_KEY)

        when: "Requesting mount skins"
        def mountSkins = accountClient.getMountSkins()

        then: "Retrieved list matches expected"
        mountSkins == [1, 2, 3, 4, 6, 103]
    }

    def "Should get unlocked mount types"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/mounts/types", "/responses/account/mount_types.json", API_KEY)

        when: "Requesting mount types"
        def mountTypes = accountClient.getMountTypes()

        then: "Retrieved list matches expected"
        mountTypes == [
                "raptor",
                "skimmer",
                "springer",
                "griffon",
                "jackal",
                "roller_beetle"
        ]
    }

    def "Should get novelties"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/novelties", "/responses/account/novelties.json", API_KEY)

        when: "Requesting novelties"
        def novelties = accountClient.getNovelties()

        then: "Retrieved list matches expected"
        novelties == [1, 2, 3]
    }

    def "Should get outfits"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/outfits", "/responses/account/outfits.json", API_KEY)

        when: "Requesting outfits"
        def outfits = accountClient.getOutfits()

        then: "Retrieved list matches expected"
        outfits == [5, 27, 44]
    }

    def "Should get pvp heroes"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/pvp/heroes", "/responses/account/pvp_heroes.json", API_KEY)

        when: "Requesting pvp heroes"
        def pvpHeroes = accountClient.getPvpHeroes()

        then: "Retrieved list matches expected"
        pvpHeroes == [1, 2, 3]
    }

    def "Should get raids"() {
        given: "Eternal api is stubbed"
        stubAuthResponse("/account/raids", "/responses/account/raids.json", API_KEY)

        when: "Requesting raids"
        def raids = accountClient.getRaids()

        then: "Retrieved list matches expected"
        raids == [
                "gorseval",
                "xera"
        ]
    }

    def "Should get recipes"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/recipes", "/responses/account/recipes.json", API_KEY)

        when: "Retrieving recipes"
        def recipes = accountClient.getRecipes()

        then: "Retrieved list matches expected"
        recipes == [104, 105, 11888, 11889]
    }

    def "Should get skins"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/skins", "/responses/account/skins.json", API_KEY)

        when: "Retrieving skins"
        def skins = accountClient.getSkins()

        then: "Retrieved list matches expected"
        skins == [1, 2, 3]
    }

    def "Should get titles"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/titles", "/responses/account/titles.json", API_KEY)

        when: "Retrieving titles"
        def titles = accountClient.getTitles()

        then: "Retrieved list matches expected"
        titles == [11, 12, 13, 190, 204, 213]
    }

    def "Should get wallet"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/wallet", "/responses/account/wallet.json", API_KEY)

        when: "Retrieved wallet"
        def wallet = accountClient.getWallet()

        then: "Retrieved wallet matches expected"
        verifyAll(wallet.get(0)) {
            id == 1
            value == 100001
        }
    }

    def "Should get world bosses"() {
        given: "External api is stubbed"
        stubAuthResponse("/account/worldbosses", "/responses/account/worldbosses.json", API_KEY)

        when: "Requesting world bosses"
        def worldbosses = accountClient.getWorldBosses()

        then: "Retrieved list matches expected"
        worldbosses == ["admiral_taidha_covington"]
    }
}
