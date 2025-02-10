package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SkillsClient : BaseHttpClient() {

    private val skillsEndpoint: String = "/skills"

    fun getSkillIds(): List<Int> {
        return getRequest(skillsEndpoint)
    }

    fun getSkills(ids: List<Int>, language: ApiLanguage?): List<Skill> {
        val params = ids.joinToString(",")
        return getRequest(skillsEndpoint, listOf("ids" to params), language)
    }

    fun getPagedSkills(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Skill>> {
        return getPagedRequest(skillsEndpoint, pageRequest.toQueryParams(), language)
    }
}