package io.github.kryszak.gwatlin.api.skills

import io.github.kryszak.gwatlin.api.ApiLanguage.EN
import io.github.kryszak.gwatlin.api.gamemechanics.GWSkillsClient
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import io.github.kryszak.gwatlin.api.items.model.item.*
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.datatest.withData
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeBlank
import io.kotest.matchers.types.beOfType
import io.kotest.matchers.types.shouldBeTypeOf

internal class SkillsClientTest : BaseWiremockTest() {

    private val skillsClient = GWSkillsClient()

    init {
        context("Get skill of type") {
            withData(
                mapOf(
                    "armor" to ItemTestInput(10607, "10607-necro-util.json", ::necroUtilAssertion),
                    "back" to ItemTestInput(10646, "10646-necro-elite.json", ::necroEliteAssertion),
                )
            ) { (id, responseFile, assertion) ->
                // given
                val lang = EN
                stubResponse("/skills?ids=$id", "/responses/skills/$responseFile", language = lang)

                // when
                val items = skillsClient.getSkills(listOf(id), lang)

                // then
                items shouldHaveSize 1
                assertion(items[0])
            }
        }
    }

    data class ItemTestInput(
        val skillId: Int,
        val responseFile: String,
        val assertion: (skill: Skill) -> Unit,
    )

    private fun necroUtilAssertion(skill: Skill) =
        assertSoftly(skill) {
            name shouldBe "Well of Darkness"
            slot shouldBe SkillSlot.UTILITY
        }
    private fun necroEliteAssertion(skill: Skill) =
        assertSoftly(skill) {
            name shouldBe "Summon Flesh Golem"
            slot shouldBe SkillSlot.ELITE
        }


}