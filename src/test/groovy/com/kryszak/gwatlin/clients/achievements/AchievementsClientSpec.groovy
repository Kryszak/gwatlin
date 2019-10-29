package com.kryszak.gwatlin.clients.achievements

import com.google.common.reflect.TypeToken
import com.kryszak.gwatlin.api.achievement.model.Achievement
import com.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import com.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import spock.lang.Subject

class AchievementsClientSpec extends AchievementStubs {

    @Subject
    def achievementsClient = new AchievementsClient()

    def "Should get list of achievement ids"() {
        given: "Expected achievement id list"
        def achievementIds = parseResponse("/responses/achievements/achievement_ids.json")

        and: "External api is stubbed"
        stubAchievementIdsResponse()

        when: "Achievement list is retrieved"
        def idsList = achievementsClient.getAchievementIdsList()

        then: "Retrieved list matches expected list"
        idsList == achievementIds
    }

    def "Should get single achievement"() {
        given: "Achievement id"
        def id = 1840

        and: "External api is stubbed"
        stubAchievementSingleResponse()

        when: "Achievement is requested"
        def achievementList = achievementsClient.getAchievementByIds([id])

        then: "Retrieved achievement matches expected"
        achievementList.size() == 1
        achievementList == parseAchievementList("/responses/achievements/achievement.json")
    }

    def "Should get list of achievements"() {
        given: "Achievement ids list"
        def ids = [1840, 910, 2258]

        and: "External api is stubbed"
        stubAchievementListResponse()

        when: "Achievement list is requested"
        def achievementList = achievementsClient.getAchievementByIds(ids)

        then: "Retrieved achievement list matches expected"
        achievementList.size() == 3
        achievementList == parseAchievementList("/responses/achievements/achievement_list.json")
    }

    def "Should throw exception when non existing achievement is requested"() {
        given: "Non existing achievement id"
        def id = 12345

        and: "External api is stubbed"
        stubAchievementListErrorResponse()

        when: "Achievement is requested"
        achievementsClient.getAchievementByIds([id])

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    def "Should get list of daily achievements"() {
        given: "Expected daily achievements"
        def dailyAchievements = parseDailyAchievementList("/responses/achievements/daily_achievements.json")

        and: "External api is stubbed"
        stubDailyAchievementListResponse()

        when: "Daily achievements are requested"
        def dailyAchievementList = achievementsClient.getDailyAchievements()

        then: "Retrieved list matches expected"
        dailyAchievementList == dailyAchievements
    }

    def "Should get list of tomorrow daily achievements"() {
        given: "Expected tomorrow daily achievements"
        def tomorrowDailyAchievements    \
                   = parseDailyAchievementList("/responses/achievements/daily_tomorrow_achievements.json")

        and: "External api is stubbed"
        stubDailyTomorrowAchievementListResponse()

        when: "Tomorrow daily achievements are requested"
        def tomorrowDailyAchievementList = achievementsClient.getTomorrowDailyAchievements()

        then: "Retrieved list matches expected"
        tomorrowDailyAchievementList == tomorrowDailyAchievements
    }

    def "Should get list of achievement group ids"() {
        given: "Expected group ids list"
        def groupIds = parseResponse("/responses/achievements/achievement_group_ids.json")

        and: "External api is stubbed"
        stubAchievementGroupIdsResponse()

        when: "Achievement group ids are requested"
        def achievementGroupIdsList = achievementsClient.getAchievementGroupIds()

        then: "Retrieved list matches expected"
        achievementGroupIdsList == groupIds
    }

    def "Should retrieve achievement group"() {
        given: "Achievement group id"
        def id = "65B4B678-607E-4D97-B458-076C3E96A810"

        and: "External api is stubbed"
        stubAchievementGroupResponse()

        when: "Achievement group is requested"
        def achievementGroup = achievementsClient.getAchievementGroup(id)

        then: "Retrieved group matches expected"
        achievementGroup == parseAchievementGroup()
    }

    def "Should retrieve achievement category ids"() {
        given: "Expected category ids"
        def categoryIds = parseResponse("/responses/achievements/achievement_categories.json")

        and: "external api is stubbed"
        stubAchievementCategoryIdsResponse()

        when: "Achievement category ids are requested"
        def achievementCategoryIdsList = achievementsClient.getAchievementCategories()

        then: "Retrieved ids match expected"
        achievementCategoryIdsList == categoryIds
    }

    def "Should retrieve achievement category"() {
        given: "Category id"
        def id = 1

        and: "External api is stubbed"
        stubAchievementCategoryResponse()

        when: "Achievement category is requested"
        def achievementCategory = achievementsClient.getAchievementCategory(id)

        then: "Retrieved category matches expected"
        achievementCategory == parseAchievementCategory()
    }

    def "Should throw exception on non existing category"() {
        given: "Non existing achievement category id"
        def id = 1000

        and: "External api is stubbed"
        stubAchievementCategoryErrorResponse()

        when: "Achievement is requested"
        achievementsClient.getAchievementCategory(id)

        then: "Exception is thrown"
        thrown(ApiRequestException)
    }

    private AchievementCategory parseAchievementCategory() {
        gson.fromJson(parseResponseText("/responses/achievements/achievement_category.json"), AchievementCategory)
    }

    private AchievementGroup parseAchievementGroup() {
        gson.fromJson(parseResponseText("/responses/achievements/achievement_group.json"), AchievementGroup)
    }

    private DailyAchievementList parseDailyAchievementList(String fileName) {
        gson.fromJson(parseResponseText(fileName), DailyAchievementList)
    }

    private List<Achievement> parseAchievementList(String fileName) {
        gson.fromJson(parseResponseText(fileName),
                new TypeToken<List<Achievement>>() {}.getType())
    }
}
