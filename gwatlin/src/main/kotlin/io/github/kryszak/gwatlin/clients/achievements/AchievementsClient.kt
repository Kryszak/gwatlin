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
        return getRequest(baseEndpoint, listOf("ids" to params))
    }

    fun getPagedAchievements(pageRequest: PageRequest): PagedResponse<List<Achievement>> {
        return getPagedRequest(baseEndpoint, pageRequest.toQueryParams())
    }

    fun getPagedAchievements(pageRequest: PageRequest): PagedResponse<List<Achievement>> {
        return getPagedRequest("$baseEndpoint?${pageRequest.toQueryParams()}")
    }

    fun getAchievementGroupIds(): List<String> {
        return getRequest(groupEndpoint)
    }

    fun getAchievementGroup(id: String): AchievementGroup {
        return getRequest("$groupEndpoint/$id")
    }

    fun getAchievementGroups(ids: List<String>): List<AchievementGroup> {
        val params = ids.joinToString(",")
        return getRequest(groupEndpoint, listOf("ids" to params))
    }

    fun getPagedAchievementGroups(pageRequest: PageRequest): PagedResponse<List<AchievementGroup>> {
        return getPagedRequest(groupEndpoint, pageRequest.toQueryParams())
    }

    fun getPagedAchievementGroups(pageRequest: PageRequest): PagedResponse<List<AchievementGroup>> {
        return getPagedRequest("$groupEndpoint?${pageRequest.toQueryParams()}")
    }

    fun getAchievementCategoryIds(): List<Int> {
        return getRequest(categoryEndpoint)
    }

    fun getAchievementCategory(id: Int): AchievementCategory {
        return getRequest("$categoryEndpoint/$id")
    }

    fun getAchievementCategories(ids: List<Int>): List<AchievementCategory> {
        val params = ids.joinToString(",")
        return getRequest(categoryEndpoint, listOf("ids" to params))
    }

    fun getPagedAchievementCategories(pageRequest: PageRequest): PagedResponse<List<AchievementCategory>> {
        return getPagedRequest(categoryEndpoint, pageRequest.toQueryParams())
    }

    fun getPagedAchievementCategories(pageRequest: PageRequest): PagedResponse<List<AchievementCategory>> {
        return getPagedRequest("$categoryEndpoint?${pageRequest.toQueryParams()}")
    }
}
