package io.github.kryszak.gwatlin.api.achievement

import io.github.kryszak.gwatlin.api.achievement.model.Achievement
import io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import io.github.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
import io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import io.github.kryszak.gwatlin.clients.achievements.AchievementsClient

/**
 * Client for achievements endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWAchievementsClient {

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
     * @see io.github.kryszak.gwatlin.api.achievement.model.Achievement
     */
    fun getAchievementsByIds(ids: List<Int>): List<Achievement> {
        return achievementsClient.getAchievementsByIds(ids)
    }

    /**
     * Retrieves list of today daily achievements
     * @return list of daily achievements
     * @see io.github.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
     */
    fun getDailyAchievements(): DailyAchievementList {
        return achievementsClient.getDailyAchievements()
    }

    /**
     * Retrieves list of tomorrow daily achievements
     * @return list of daily achievements
     * @see io.github.kryszak.gwatlin.api.achievement.model.daily.DailyAchievementList
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
     * @see io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
     */
    fun getAchievementGroup(id: String): AchievementGroup {
        return achievementsClient.getAchievementGroup(id)
    }

    /**
     * Retrieves list of all existing achievement category ids
     * @return list of achievement category ids
     */
    fun getAchievementCategoryIds(): List<Int> {
        return achievementsClient.getAchievementCategoryIds()
    }

    /**
     * Retrieves specific achievement category
     * @param id of achievement category
     * @return Achievement category
     * @see io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
     */
    fun getAchievementCategory(id: Int): AchievementCategory {
        return achievementsClient.getAchievementCategory(id)
    }
}
