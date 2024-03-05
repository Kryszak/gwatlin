package io.github.kryszak.gwatlin.api.skills

import io.github.kryszak.gwatlin.api.ApiLanguage.EN
import io.github.kryszak.gwatlin.api.gamemechanics.GWSkillsClient
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.AttributeAdjust
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Range
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Recharge
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.SkillType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

internal class SkillsClientTest : BaseWiremockTest() {

    private val skillsClient = GWSkillsClient()

    init {
        context("Get skill of type") {
            withData(
                mapOf(
                    "necro-util" to SkillTestInput(10607, "10607-necro-util.json", ::necroUtilAssertion),
                    "necro-elite" to SkillTestInput(10646, "10646-necro-elite.json", ::necroEliteAssertion),
                    "revenant-heal" to SkillTestInput(62719, "62719-revenant-heal.json", ::revenantHealAssertion)
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

    data class SkillTestInput(
        val skillId: Int,
        val responseFile: String,
        val assertion: (skill: Skill) -> Unit,
    )

    private fun necroUtilAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Well of Darkness"
        slot shouldBe SkillSlot.UTILITY
        description shouldBe "Well. Target area pulses, blinding foes with each pulse."
        weaponType shouldBe "None"
        chatLink shouldBe "[&Bm8pAAA=]"
        type shouldBe SkillType.UTILITY
        skillType.shouldBeNull()
        professions shouldContainExactly listOf("Necromancer")
        slot shouldBe SkillSlot.UTILITY
        facts shouldHaveSize 10
        assertSoftly(facts[0]) {
            text shouldBe "Range"
            shouldBeTypeOf<Range>()
            value shouldBe 900
        }
        flags shouldContainExactly listOf("GroundTargeted")
        traitedFacts.shouldBeEmpty()
        categories shouldContainExactly listOf("Well")
        attunement.shouldBeNull()
        cost.shouldBeNull()
        dualWield.shouldBeNull()
        flipSkill.shouldBeNull()
        initiative.shouldBeNull()
        nextChain.shouldBeNull()
        previousChain.shouldBeNull()
    }

    private fun necroEliteAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Summon Flesh Golem"
        slot shouldBe SkillSlot.ELITE
        description shouldBe "Minion. Summon a flesh golem to attack foes with crippling claws."
        weaponType shouldBe "None"
        chatLink shouldBe "[&BpYpAAA=]"
        type shouldBe SkillType.ELITE
        skillType.shouldBeNull()
        professions shouldContainExactly listOf("Necromancer")
        slot shouldBe SkillSlot.ELITE
        facts shouldHaveSize 4
        assertSoftly(facts[0]) {
            text shouldBe "Recharge"
            shouldBeTypeOf<Recharge>()
            value shouldBe 48
        }
        flags.shouldBeEmpty()
        traitedFacts shouldHaveSize 2
        assertSoftly(traitedFacts[0]) {
            requiresTrait shouldBe 858
            overrides shouldBe 1
            // TODO TraitedFacts type not done yet
            // shouldBeTypeOf<Damage>()
            // text shouldBe "Damage"
            // hitCount shouldBe 1
            // dmgMultiplier shouldBe (0.375 plusOrMinus 0.01)
        }
        categories shouldContainExactly listOf("Minion")
        attunement.shouldBeNull()
        cost.shouldBeNull()
        dualWield.shouldBeNull()
        flipSkill shouldBe 10647
        initiative.shouldBeNull()
        nextChain.shouldBeNull()
        previousChain.shouldBeNull()
    }

    private fun revenantHealAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Selfish Spirit"
        slot shouldBe SkillSlot.HEAL
        description shouldBe "Legendary Alliance. Channel your rage into the nearby area, healing and empowering yourself for each enemy struck."
        weaponType shouldBe "None"
        chatLink shouldBe "[&Bv/0AAA=]"
        type shouldBe SkillType.HEAL
        skillType.shouldBeNull()
        professions shouldContainExactly listOf("Revenant")
        slot shouldBe SkillSlot.HEAL
        facts shouldHaveSize 9
        assertSoftly(facts[3]) {
            text shouldBe "Healing per Hit"
            shouldBeTypeOf<AttributeAdjust>()
            value shouldBe 714
            target shouldBe "Healing"
        }
        flags.shouldBeEmpty()
        traitedFacts.shouldBeEmpty()
        categories.shouldBeEmpty()
        attunement.shouldBeNull()
        cost shouldBe 10
        dualWield.shouldBeNull()
        flipSkill shouldBe 62680
        initiative.shouldBeNull()
        nextChain.shouldBeNull()
        previousChain.shouldBeNull()
    }


}