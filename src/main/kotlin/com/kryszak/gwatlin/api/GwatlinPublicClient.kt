package com.kryszak.gwatlin.api

import com.kryszak.gwatlin.achievements.AchievementsClient
import com.kryszak.gwatlin.api.model.achievement.Achievement
import com.kryszak.gwatlin.api.model.achievement.category.AchievementCategory
import com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
import com.kryszak.gwatlin.api.model.achievement.group.AchievementGroup

/**
 * Client for public endpoints not requiring authentication with ApiKey
 */
class GwatlinPublicClient {

    private val achievementsClient: AchievementsClient = AchievementsClient()

    /**
     * Retrieves list of all existing achievement ids
     * @return list of achievement ids
     */
    fun getAchievementIdsList(): List<Int> {
        return achievementsClient.getAchievementIdsList()
    }

    /**
     * Retrieves list of achievements by given list of ids
     * @param ids list of requested achievement ids
     * @return list of Achievements
     * @see com.kryszak.gwatlin.api.model.achievement.Achievement
     */
    fun getAchievementsByIds(ids: List<Int>): List<Achievement> {
        return achievementsClient.getAchievementByIds(ids)
    }

    /**
     * Retrieves list of today daily achievements
     * @return list of daily achievements
     * @see com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
     */
    fun getDailyAchievements(): DailyAchievementList {
        return achievementsClient.getDailyAchievements()
    }

    /**
     * Retrieves list of tomorrow daily achievements
     * @return list of daily achievements
     * @see com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
     */
    fun getDailyTomorrowAchievements(): DailyAchievementList {
        return achievementsClient.getTomorrowDailyAchievements()
    }

    /**
     * Retrieves list of all existing achievement group ids
     * @return list of achievement category ids
     */
    fun getAchievementGroupIds(): List<String> {
        return achievementsClient.getAchievementGroupIds()
    }

    /**
     * Retrieves specific achievement group
     * @param id of achievement group
     * @return Achievement group
     * @see com.kryszak.gwatlin.api.model.achievement.group.AchievementGroup
     */
    fun getAchievementGroup(id: String): AchievementGroup {
        return achievementsClient.getAchievementGroup(id)
    }

    /**
     * Retrieves list of all existing achievement category ids
     * @return list of achievement category ids
     */
    fun getAchievementCategoryIds(): List<Int> {
        return achievementsClient.getAchievementIdsList()
    }

    /**
     * Retrieves specific achievement category
     * @param id of achievement category
     * @return Achievement category
     * @see com.kryszak.gwatlin.api.model.achievement.category.AchievementCategory
     */
    fun getAchievementCategory(id: Int): AchievementCategory {
        return achievementsClient.getAchievementCategory(id)
    }
}
