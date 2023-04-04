package com.kryszak.gwatlin.api.achievement


import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class AchievementsClientSpec extends WiremockTest {

    @Subject
    def achievementsClient = new GWAchievementsClient()

    def "Should get list of achievement ids"() {
        given: "External api is stubbed"
        stubResponse("/achievements", "/responses/achievements/achievement_ids.json")

        when: "Achievement list is retrieved"
        def idsList = achievementsClient.getAchievementIdsList()

        then: "Retrieved list matches expected list"
        idsList.size() == 3392
    }

    def "Should get single achievement"() {
        given: "Achievement id"
        def id = 1840

        and: "External api is stubbed"
        stubResponse("/achievements?ids=1840", "/responses/achievements/achievement.json")

        when: "Achievement is requested"
        def achievementList = achievementsClient.getAchievementsByIds([id])

        then: "Retrieved achievement matches expected"
        achievementList.size() == 1
        verifyAll(achievementList.get(0)) {
            id == 1840
            name == "Daily Completionist"
            description == ""
            requirement == "Complete any  PvE, WvW, or PvP Daily Achievements."
            lockedText == ""
            type == "Default"
            flags == ["Pvp", "CategoryDisplay", "Daily"]
            verifyAll(tiers.get(0)) {
                count == 3
                points == 10
            }
            verifyAll(rewards.get(0)) {
                type == "Item"
                count == 1
            }
        }
    }

    def "Should get list of achievements"() {
        given: "Achievement ids list"
        def ids = [1840, 910, 2258]

        and: "External api is stubbed"
        stubResponse("/achievements?ids=1840,910,2258", "/responses/achievements/achievement_list.json")

        when: "Achievement list is requested"
        def achievementList = achievementsClient.getAchievementsByIds(ids)

        then: "Retrieved achievement list matches expected"
        achievementList.size() == 3
    }

    def "Should throw exception when non existing achievement is requested"() {
        given: "Non existing achievement id"
        def id = 12345

        and: "External api is stubbed"
        stubNotFoundResponse("/achievements?ids=12345", "/responses/achievements/achievement_error.json")

        when: "Achievement is requested"
        achievementsClient.getAchievementsByIds([id])

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get list of daily achievements"() {
        given: "External api is stubbed"
        stubResponse("/achievements/daily", "/responses/achievements/daily_achievements.json")

        when: "Daily achievements are requested"
        def dailyAchievementList = achievementsClient.getDailyAchievements()

        then: "Retrieved list matches expected"
        verifyAll(dailyAchievementList.pve.get(0)) {
            id == 1837
            level.min == 1
            level.max == 80
            requiredAccess == ["GuildWars2", "HeartOfThorns", "PathOfFire"]
        }
    }

    def "Should get list of tomorrow daily achievements"() {
        given: "External api is stubbed"
        stubResponse("/achievements/daily/tomorrow", "/responses/achievements/daily_tomorrow_achievements.json")

        when: "Tomorrow daily achievements are requested"
        def tomorrowDailyAchievementList = achievementsClient.getDailyTomorrowAchievements()

        then: "Retrieved list matches expected"
        verifyAll(tomorrowDailyAchievementList.pve.get(0)) {
            id == 3880
            level.min == 80
            level.max == 80
            requiredAccess == ["GuildWars2", "HeartOfThorns", "PathOfFire"]
        }
    }

    def "Should get list of achievement group ids"() {
        given: "External api is stubbed"
        stubResponse("/achievements/groups", "/responses/achievements/achievement_group_ids.json")

        when: "Achievement group ids are requested"
        def achievementGroupIdsList = achievementsClient.getAchievementGroupIds()

        then: "Retrieved list matches expected"
        achievementGroupIdsList.size() == 11
    }

    def "Should retrieve achievement group"() {
        given: "Achievement group id"
        def id = "65B4B678-607E-4D97-B458-076C3E96A810"

        and: "External api is stubbed"
        stubResponse("/achievements/groups/65B4B678-607E-4D97-B458-076C3E96A810", "/responses/achievements/achievement_group.json")

        when: "Achievement group is requested"
        def achievementGroup = achievementsClient.getAchievementGroup(id)

        then: "Retrieved group matches expected"
        verifyAll(achievementGroup) {
            id == "65B4B678-607E-4D97-B458-076C3E96A810"
            name == "Heart of Thorns"
            description == "Achievements for accomplishments throughout the jungle."
            order == 6
            categories.size() == 6
        }
    }

    def "Should retrieve achievement category ids"() {
        given: "external api is stubbed"
        stubResponse("/achievements/categories", "/responses/achievements/achievement_categories.json")

        when: "Achievement category ids are requested"
        def achievementCategoryIdsList = achievementsClient.getAchievementCategoryIds()

        then: "Retrieved ids match expected"
        achievementCategoryIdsList.size() == 196
    }

    def "Should retrieve achievement category"() {
        given: "Category id"
        def id = 1

        and: "External api is stubbed"
        stubResponse("/achievements/categories/1", "/responses/achievements/achievement_category.json")

        when: "Achievement category is requested"
        def achievementCategory = achievementsClient.getAchievementCategory(id)

        then: "Retrieved category matches expected"
        verifyAll(achievementCategory) {
            id == 1
            name == "Slayer"
            description == ""
            order == 10
            icon == "https://render.guildwars2.com/file/E00460A2CAD85D47406EAB4213D1010B3E80C9B0/42675.png"
            achievements.size() == 44
        }
    }

    def "Should throw exception on non existing category"() {
        given: "Non existing achievement category id"
        def id = 1000

        and: "External api is stubbed"
        stubNotFoundResponse("/achievements/categories/1000", "/responses/achievements/achievement_category_error.json")

        when: "Achievement is requested"
        achievementsClient.getAchievementCategory(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }
}
