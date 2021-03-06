package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import com.kryszak.gwatlin.http.BaseHttpClient

internal class SkillsClient : BaseHttpClient() {

    private val skillsEndpoint: String = "skills"

    fun getSkillIds(): List<Int> {
        return getRequest(skillsEndpoint)
    }

    fun getSkills(ids: List<Int>, language: String): List<Skill> {
        val params = ids.joinToString(",")
        return getRequest("$skillsEndpoint?ids=$params&lang=$language")
    }
}