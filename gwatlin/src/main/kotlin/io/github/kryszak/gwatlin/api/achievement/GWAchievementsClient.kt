package io.github.kryszak.gwatlin.api.achievement

import io.github.kryszak.gwatlin.api.achievement.model.Achievement
import io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.achievements.AchievementsClient

/**
 * Client for achievements endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/achievements).
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
     * Retrieves paged achievements list
     */
    fun getPagedAchievements(page: PageRequest): PagedResponse<List<Achievement>> {
        return achievementsClient.getPagedAchievements(page)
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
     * @return achievement group
     * @see io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
     */
    fun getAchievementGroup(id: String): AchievementGroup {
        return achievementsClient.getAchievementGroup(id)
    }

    /**
     * Retrieves list of achievement groups
     * @param ids of achievement groups
     * @return list of achievement groups
     * @see io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
     */
    fun getAchievementGroups(ids: List<String>): List<AchievementGroup> {
        return achievementsClient.getAchievementGroups(ids)
    }

    /**
     * Retrieves paged list of achievement groups
     */
    fun getPagedAchievementGroups(pageRequest: PageRequest): PagedResponse<List<AchievementGroup>> {
        return achievementsClient.getPagedAchievementGroups(pageRequest)
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
     * @return achievement category
     * @see io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
     */
    fun getAchievementCategory(id: Int): AchievementCategory {
        return achievementsClient.getAchievementCategory(id)
    }

    /**
     * Retrieves achievement categories
     * @param ids list of achievement category ids
     * @return list of achievement categories
     * @see io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
     */
    fun getAchievementCategories(ids: List<Int>): List<AchievementCategory> {
        return achievementsClient.getAchievementCategories(ids)
    }

    /**
     * Returns paged achievement categories
     */
    fun getPagedAchievementCategories(pageRequest: PageRequest): PagedResponse<List<AchievementCategory>> {
        return achievementsClient.getPagedAchievementCategories(pageRequest)
    }
}
