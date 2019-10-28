package com.kryszak.gwatlin.clients.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import spock.lang.Subject

class SkillsClientSpec extends GameMechanicsStubs {

    @Subject
    def skillsClient = new SkillsClient()

    def "Should get skill ids list"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/gamemechanics/skill_ids.json")

        and: "External api is stubbed"
        stubSkillIdsResponse()

        when: "Requesting skill ids"
        def skillIds = skillsClient.getSkillIds()

        then: "Retrieved list matches expected"
        skillIds == ids
    }

    def "Should get skills"() {
        given: "Skill ids"
        def ids = [1110, 1115]

        and: "External api is stubbed"
        stubSkillsResponse()

        when: "Skills are requested"
        def skills = skillsClient.getSkills(ids, "en")

        then: "Retrieved list matches expected"
        skills == parseSkills()
    }

    private List<Skill> parseSkills() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/skills.json"),
                new TypeToken<List<Skill>>() {}.getType())
    }
}
