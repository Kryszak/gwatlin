package com.kryszak.gwatlin.api.gamemechanics

import com.google.gson.reflect.TypeToken
import com.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillType
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class SkillsClientSpec extends WiremockConfig {

    @Subject
    def skillsClient = new GWSkillsClient()

    def "Should get skill ids list"() {
        given: "Expected ids list"
        def ids = parseResponse("/responses/gamemechanics/skill_ids.json")

        and: "External api is stubbed"
        stubResponse("/skills", "/responses/gamemechanics/skill_ids.json")

        when: "Requesting skill ids"
        def skillIds = skillsClient.getSkillIds()

        then: "Retrieved list matches expected"
        skillIds == ids
    }

    def "Should get skills"() {
        given: "Skill ids"
        def ids = [1110, 1115]

        and: "External api is stubbed"
        stubResponse("/skills?ids=1110,1115&lang=en", "/responses/gamemechanics/skills.json")

        when: "Skills are requested"
        def skills = skillsClient.getSkills(ids, "en")

        then: "Retrieved list matches expected"
        skills == parseSkills()
        verifyAll(skills.get(0)) {
            name == 'Throw Gunk'
            facts.size() == 5
            description == "Throw gunk at target area to inflict a random condition."
            icon == "https://render.guildwars2.com/file/3A487770D4A0E006D0A0E57C68A639BF7003A5BC/102940.png"
            type == SkillType.PROFESSION
            weaponType == "None"
            professions.size() == 0
            slot == SkillSlot.PROFESSION2
            flags.size() == 2
            id == 1110
            chatLink == "[&BlYEAAA=]"
        }
    }

    private List<Skill> parseSkills() {
        gson.fromJson(parseResponseText("/responses/gamemechanics/skills.json"),
                new TypeToken<List<Skill>>() {}.getType())
    }
}
