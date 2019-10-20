package com.kryszak.gwatlin.api

import com.kryszak.gwatlin.clients.achievements.AchievementsClient
import com.kryszak.gwatlin.api.model.achievement.Achievement
import com.kryszak.gwatlin.api.model.achievement.category.AchievementCategory
import com.kryszak.gwatlin.api.model.achievement.daily.DailyAchievementList
import com.kryszak.gwatlin.api.model.achievement.group.AchievementGroup
import com.kryszak.gwatlin.api.model.homeinstance.Cat
import com.kryszak.gwatlin.api.model.homeinstance.Node
import com.kryszak.gwatlin.clients.homeinstance.HomeInstanceClient

/**
 * Client for public endpoints not requiring authentication with ApiKey
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GwatlinPublicClient {

    private val achievementsClient: AchievementsClient = AchievementsClient()

    private val homeInstanceClient: HomeInstanceClient = HomeInstanceClient()

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

    /**
     * Retrieves list of all cat ids
     * @return list of cat ids
     */
    fun getCatIds(): List<Int> {
        return homeInstanceClient.getCatIds()
    }

    /**
     * Retrieves specific cat
     * @param id of cat
     * @return Cat
     * @see com.kryszak.gwatlin.api.model.homeinstance.Cat
     */
    fun getCat(id: Int): Cat {
        return homeInstanceClient.getCat(id)
    }

    /**
     * Retrieves list of cats
     * @param ids of cats
     * @return List of cats
     * @see com.kryszak.gwatlin.api.model.homeinstance.Cat
     */
    fun getCats(ids: List<Int>): List<Cat> {
        return homeInstanceClient.getCats(ids)
    }

    /**
     * Retrieves list of all available home node instances
     * @return List of nodes
     */
    fun getNodeIds(): List<String> {
        return homeInstanceClient.getNodesIds()
    }

    /**
     * Retrieves specific home node instance
     * @return Node
     * @see com.kryszak.gwatlin.api.model.homeinstance.Node
     */
    fun getNode(id: String): Node {
        return homeInstanceClient.getNode(id)
    }
}
