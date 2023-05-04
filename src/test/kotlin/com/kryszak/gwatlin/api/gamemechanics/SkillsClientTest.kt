package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import com.kryszak.gwatlin.api.gamemechanics.model.skill.SkillType
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class SkillsClientTest : BaseWiremockTest() {

    private val skillsClient = GWSkillsClient()

    init {
        should("Get skill ids list") {
            // given
            stubResponse("/skills", "/responses/gamemechanics/skill_ids.json")

            // when
            val skillIds = skillsClient.getSkillIds()

            // then
            skillIds shouldHaveSize 3060
        }

        should("Get skills") {
            // given
            val ids = listOf(1110, 1115)
            val lang = ApiLanguage.EN

            stubResponse("/skills?ids=1110,1115", "/responses/gamemechanics/skills.json", language = lang)

            // when
            val skills = skillsClient.getSkills(ids, lang)

            // then
            assertSoftly(skills[0]) {
                name shouldBe "Throw Gunk"
                description shouldBe "Throw gunk at target area to inflict a random condition."
                icon shouldBe "https://render.guildwars2.com/file/3A487770D4A0E006D0A0E57C68A639BF7003A5BC/102940.png"
                type shouldBe SkillType.PROFESSION
                weaponType shouldBe "None"
                professions shouldHaveSize 0
                slot shouldBe SkillSlot.PROFESSION2
                flags shouldHaveSize 2
                id shouldBe 1110
                chatLink shouldBe "[&BlYEAAA=]"
                assertSoftly(facts[0]) {
                    text shouldBe "Range"
                    type shouldBe "Range"
                    icon shouldBe "https://render.guildwars2.com/file/0AAB34BEB1C9F4A25EC612DDBEACF3E20B2810FA/156666.png"
                    value shouldBe 900
                }
            }
        }
    }
}