package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.*
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.SkillSlot
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.SkillType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.datatest.withData
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeTypeOf

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
                    "corrosive-residue" to SkillTestInput(
                        34569,
                        "34569-corrosive-residue.json",
                        ::corrosiveResidueAssertion
                    ),
                    "engage-photon-forge" to SkillTestInput(
                        42938,
                        "42938-engage-photon-forge.json",
                        ::engagePhotonForgeAssertion
                    ),
                    "path-of-gluttony" to SkillTestInput(
                        71867,
                        "71867-path-of-gluttony.json",
                        ::pathOfGluttonyAssertion
                    ),
                    "signet-of-the-locust" to SkillTestInput(
                        10612,
                        "10612-signet-of-the-locust.json",
                        ::signetOfTheLocustAssertion
                    ),
                    "sparking-aura" to SkillTestInput(
                        75270,
                        "75270-sparking-aura.json",
                        ::sparkingAuraAssertion
                    ),
                    "glyph-of-elemental-power" to SkillTestInput(
                        5506,
                        "5506-glyph-of-elemental-power.json",
                        ::glyphOfElementalPowerAssertion
                    ),
                    "fulgor" to SkillTestInput(73091, "73091-fulgor.json", ::fulgorAssertion),
                    "whirling-axe" to SkillTestInput(
                        1162, "1162-whirling-axe.json", ::whirlingAxeAssertion
                    )
                )
            ) { (id, responseFile, assertion) ->
                // given
                val lang = ApiLanguage.EN
                stubResponse("/v2/skills?ids=$id", "/responses/gamemechanics/skills/$responseFile", language = lang)

                // when
                val items = skillsClient.getSkills(listOf(id), lang)

                // then
                items shouldHaveSize 1
                assertion(items[0])
            }
        }
        context("Get general skills") {
            should("Get skill ids list") {
                // given
                stubResponse("/v2/skills", "/responses/gamemechanics/skills/skill_ids.json")

                // when
                val skillIds = skillsClient.getSkillIds()

                // then
                skillIds shouldHaveSize 3060
            }

            should("Get skills") {
                // given
                val ids = listOf(1110, 1115)
                val lang = ApiLanguage.EN

                stubResponse("/v2/skills?ids=1110,1115", "/responses/gamemechanics/skills/skills.json", language = lang)

                // when
                val skills = skillsClient.getSkills(ids, lang)

                // then
                assertSoftly(skills[0]) {
                    name shouldBe "Throw Gunk"
                    description shouldBe "Throw gunk at target area to inflict a random condition."
                    icon shouldBe "https://render.guildwars2.com/file/3A487770D4A0E006D0A0E57C68A639BF7003A5BC/102940.png"
                    type shouldBe SkillType.PROFESSION
                    weaponType shouldBe "None"
                    assertSoftly(professions) {
                        it.shouldNotBeNull()
                        it.shouldBeEmpty()
                    }
                    slot shouldBe SkillSlot.PROFESSION_2
                    flags shouldHaveSize 2
                    id shouldBe 1110
                    chatLink shouldBe "[&BlYEAAA=]"
                    assertSoftly(facts[0]) {
                        text shouldBe "Range"
                        type shouldBe "Range"
                        icon shouldBe "https://render.guildwars2.com/file/0AAB34BEB1C9F4A25EC612DDBEACF3E20B2810FA/156666.png"
                    }
                }
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
        assertSoftly(facts[2]) {
            shouldBeTypeOf<Time>()
            text shouldBe "Duration"
            type shouldBe "Time"
            icon shouldBe "https://render.guildwars2.com/file/7B2193ACCF77E56C13E608191B082D68AA0FAA71/156659.png"
            duration shouldBe 5
        }
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

    private fun signetOfTheLocustAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Signet of the Locust"
        assertSoftly(facts[3]) {
            shouldBeInstanceOf<HealingAdjust>()
            text shouldBe "Healing per Hit"
            type shouldBe "HealingAdjust"
            icon shouldBe "https://render.guildwars2.com/file/D4347C52157B040943051D7E09DEAD7AF63D4378/156662.png"
            hitCount shouldBe 1
        }
    }

    private fun sparkingAuraAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Sparking Aura"
        assertSoftly(facts[0]) {
            shouldBeInstanceOf<Unblockable>()
            text shouldBe "Unblockable"
            type shouldBe "Unblockable"
            icon shouldBe "https://render.guildwars2.com/file/9352ED3244417304995F26CB01AE76BB7E547052/156661.png"
            value.shouldBeTrue()
        }
    }

    private fun glyphOfElementalPowerAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Glyph of Elemental Power"
        assertSoftly(facts[1]) {
            shouldBeInstanceOf<StunBreak>()
            text shouldBe "StunBreak"
            type shouldBe "StunBreak"
            icon shouldBe "https://render.guildwars2.com/file/DCF0719729165FD8910E034CA4E0780F90582D15/156654.png"
            value.shouldBeTrue()
        }
    }

    private fun fulgorAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Fulgor"
        assertSoftly(facts[4]) {
            shouldBeInstanceOf<Radius>()
            text shouldBe "Radius"
            type shouldBe "Radius"
            icon shouldBe "https://render.guildwars2.com/file/B0CD8077991E4FB1622D2930337ED7F9B54211D5/156665.png"
            distance shouldBe 180
        }
    }

    private fun whirlingAxeAssertion(skill: Skill) = assertSoftly(skill) {
        name shouldBe "Whirling Axe"
        assertSoftly(facts[4]) {
            shouldBeInstanceOf<NoData>()
            text shouldBe "Reflects Missiles"
            type shouldBe "NoData"
            icon shouldBe "https://render.guildwars2.com/file/9352ED3244417304995F26CB01AE76BB7E547052/156661.png"
        }
    }
}

