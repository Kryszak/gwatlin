package io.github.kryszak.gwatlin.clients.achievements

import io.github.kryszak.gwatlin.api.achievement.model.Achievement
import io.github.kryszak.gwatlin.api.achievement.model.category.AchievementCategory
import io.github.kryszak.gwatlin.api.achievement.model.group.AchievementGroup
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class AchievementsClient : BaseHttpClient() {

    private val baseEndpoint: String = "/achievements"

    private val groupEndpoint: String = "$baseEndpoint/groups"

    private val categoryEndpoint: String = "$baseEndpoint/categories"

    fun getAchievementIdsList(): List<Int> {
        return getRequest(baseEndpoint)
    }

    fun getAchievementsByIds(ids: List<Int>): List<Achievement> {
        val params = ids.joinToString(",")
        return getRequest("$baseEndpoint?ids=$params")
    }

    fun getPagedAchievements(page: PageRequest): PagedResponse<List<Achievement>> {
        return getPagedRequest("$baseEndpoint?page=${page.page}&page_size=${page.size}")
    }

    fun getAchievementGroupIds(): List<String> {
        return getRequest(groupEndpoint)
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        return getRequest("$groupEndpoint/$id")
    }

    fun getAchievementGroups(ids: List<String>): List<AchievementGroup> {
        val params = ids.joinToString(",")
        return getRequest("$groupEndpoint?ids=$params")
    }

    fun getPagedAchievementGroups(pageRequest: PageRequest): PagedResponse<List<AchievementGroup>> {
        return getPagedRequest("$groupEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}")
    }

    fun getAchievementCategoryIds(): List<Int> {
        return getRequest(categoryEndpoint)
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        return getRequest("$categoryEndpoint/$id")
    }

    fun getAchievementCategories(ids: List<Int>): List<AchievementCategory> {
        val params = ids.joinToString(",")
        return getRequest("$categoryEndpoint?ids=$params")
    }

    fun getPagedAchievementCategories(pageRequest: PageRequest): PagedResponse<List<AchievementCategory>> {
        return getPagedRequest("$categoryEndpoint?page=${pageRequest.page}&page_size=${pageRequest.size}")
    }
}
