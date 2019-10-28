package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import com.kryszak.gwatlin.clients.gamemechanics.SkillsClient

/**
 * Client for game mechanic - skills endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWSkillsClient {

    private val skillsClient: SkillsClient = SkillsClient()

    /**
     * Retrieves all skill ids
     * @return list of skill ids
     */
    fun getSkillIds(): List<Int> {
        return skillsClient.getSkillIds()
    }

    /**
     * Retrieves specific skills
     * @param ids of skills
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
     */
    fun getSkills(ids: List<Int>, language: String = "en"): List<Skill> {
        return skillsClient.getSkills(ids, language)
    }
}