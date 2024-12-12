package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SkillsClient : BaseHttpClient() {

    private val skillsEndpoint: String = "/skills"

    fun getSkillIds(): List<Int> {
        return getRequest(skillsEndpoint)
    }

    fun getSkills(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Skill> {
        val params = ids.joinToString(",")
        return getRequest("$skillsEndpoint?ids=$params", language)
    }
}