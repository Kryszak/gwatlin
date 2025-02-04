package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.gamemechanics.SkillsClient

/**
 * Client for game mechanic - skills endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/skills).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
     */
    @JvmOverloads
    fun getSkills(ids: List<Int>, language: ApiLanguage? = null): List<Skill> {
        return skillsClient.getSkills(ids, language)
    }

    fun getPagedSkills(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Skill>> {
        return skillsClient.getPagedSkills(pageRequest, language)
    }
}