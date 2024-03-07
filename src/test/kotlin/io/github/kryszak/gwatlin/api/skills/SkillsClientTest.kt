package io.github.kryszak.gwatlin.api.skills

import io.github.kryszak.gwatlin.api.ApiLanguage.EN
import io.github.kryszak.gwatlin.api.gamemechanics.GWSkillsClient
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.AttributeAdjust
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Percent
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
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.beInstanceOf
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf
import org.w3c.dom.Attr

internal class SkillsClientTest : BaseWiremockTest() {

    private val skillsClient = GWSkillsClient()

    init {
        context("Get skill of type") {
            withData(
                mapOf(
                    "necro-util" to SkillTestInput(10607, "10607-necro-util.json", ::necroUtilAssertion),
                    "necro-elite" to SkillTestInput(10646, "10646-necro-elite.json", ::necroEliteAssertion),
                    "revenant-heal" to SkillTestInput(62719, "62719-revenant-heal.json", ::revenantHealAssertion),
                    "bandage" to SkillTestInput(1175, "1175-bandage.json", ::bandageAssertion),
                    "binding-roots" to SkillTestInput(1279, "1279-binding-roots.json", ::bindingRootsAssertion),
                    "frozen-burst" to SkillTestInput(5487, "5487-frozen-burst.json", ::frozenBurstAssertion),
                    "frozen-burst" to SkillTestInput(5489, "5489-lightning-whip.json", ::lightningWhipAssertion),
                    "comet" to SkillTestInput(5490, "5490-comet.json", ::cometAssertion),
                    "grenade-barrage" to SkillTestInput(5810, "5810-grenade-barrage.json", ::grenadeBarrageAssertion),
                    "kick" to SkillTestInput(10180, "10180-kick.json", ::kickAssertion),
                    "locust-swarm" to SkillTestInput(10557, "10557-locust-swarm.json", ::locustSwarmAssertion),
                    "blood-swarm" to SkillTestInput(12424, "12424-blood-frenzy.json", ::bloodFrenzyAssertion),
                    "icy-bite" to SkillTestInput(12656, "12656-icy-bite.json", ::icyBiteAssertion),
                    "corrosive-residue" to SkillTestInput(34569,"34569-corrosive-residue.json", ::corrosiveResidueAssertion),
                    "engage-photon-forge" to SkillTestInput(42938,"42938-engage-photon-forge.json", ::engagePhotonForgeAssertion),
                    "path-of-gluttony" to SkillTestInput(71867,"71867-path-of-gluttony.json",::pathOfGluttonyAssertion),
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

    private fun bandageAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Bandage"

        assertSoftly(professions) {
            it.shouldNotBeNull()
            it shouldHaveSize 9
        }
        slot shouldBe SkillSlot.DOWNED_4
    }

    private fun bindingRootsAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Binding Roots"
        weaponType.shouldBeNull()
        type.shouldBeNull()
        professions.shouldBeNull()
        slot.shouldBeNull()
    }

    private fun frozenBurstAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Frozen Burst"
        slot shouldBe SkillSlot.WEAPON_3
    }

    private fun lightningWhipAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Lightning Whip"
        slot shouldBe SkillSlot.WEAPON_1
    }

    private fun cometAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Comet"
        slot shouldBe SkillSlot.WEAPON_5
    }

    private fun grenadeBarrageAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Grenade Barrage"
        type shouldBe SkillType.TOOLBELT
        slot shouldBe SkillSlot.TOOLBELT
    }

    private fun kickAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Kick"
        type shouldBe SkillType.MONSTER
        slot shouldBe SkillSlot.DOWNED_2
    }

    private fun locustSwarmAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Locust Swarm"
        facts shouldHaveSize 12
        assertSoftly(facts[4]) {
            shouldBeInstanceOf<Percent>()
            text shouldBe "Life Force"
            type shouldBe "Percent"
            percent shouldBe (1.5 plusOrMinus 0.01)
        }
    }

    private fun bloodFrenzyAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Blood Frenzy"
        facts shouldHaveSize 6
        assertSoftly(facts[3]) {
            shouldBeInstanceOf<AttributeAdjust>()
            value.shouldBeNull()
        }
    }

    private fun icyBiteAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Icy Bite"
        type shouldBe SkillType.PET
    }

    private fun corrosiveResidueAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Corrosive Residue"
        icon.shouldBeNull()
    }

    private fun engagePhotonForgeAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Engage Photon Forge"
        facts shouldHaveSize 4
        assertSoftly(facts[1]) {
            shouldBeTypeOf<Percent>()
            percent shouldBe 2
            value.shouldBeNull()
        }
        assertSoftly(facts[2]) {
            shouldBeTypeOf<Percent>()
            percent.shouldBeNull()
            value shouldBe 100
        }
    }

    private fun pathOfGluttonyAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Path of Gluttony"
        facts shouldHaveSize 7
        assertSoftly(facts[1]) {
            shouldBeTypeOf<AttributeAdjust>()
            target.shouldBeNull()
        }
        assertSoftly(facts[2]) {
            shouldBeTypeOf<AttributeAdjust>()
            target shouldBe "Healing"
        }
    }

}