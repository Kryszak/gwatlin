package com.kryszak.gwatlin.api.gamemechanics


import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillType
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class SkillsClientSpec extends WiremockTest {

    @Subject
    def skillsClient = new GWSkillsClient()

    def "Should get skill ids list"() {
        given: "External api is stubbed"
        stubResponse("/skills", "/responses/gamemechanics/skill_ids.json")

        when: "Requesting skill ids"
        def skillIds = skillsClient.getSkillIds()

        then: "Retrieved list matches expected"
        skillIds.size() == 3060
    }

    def "Should get skills"() {
        given: "Skill ids"
        def ids = [1110, 1115]

        and: "External api is stubbed"
        stubResponse("/skills?ids=1110,1115&lang=en", "/responses/gamemechanics/skills.json")

        when: "Skills are requested"
        def skills = skillsClient.getSkills(ids, "en")

        then: "Retrieved list matches expected"
        verifyAll(skills.get(0)) {
            name == 'Throw Gunk'
            description == "Throw gunk at target area to inflict a random condition."
            icon == "https://render.guildwars2.com/file/3A487770D4A0E006D0A0E57C68A639BF7003A5BC/102940.png"
            type == SkillType.PROFESSION
            weaponType == "None"
            professions.size() == 0
            slot == SkillSlot.PROFESSION2
            flags.size() == 2
            id == 1110
            chatLink == "[&BlYEAAA=]"
            verifyAll(facts.get(0)) {
                text == "Range"
                type == "Range"
                icon == "https://render.guildwars2.com/file/0AAB34BEB1C9F4A25EC612DDBEACF3E20B2810FA/156666.png"
                value == 900
            }
        }
    }
}
