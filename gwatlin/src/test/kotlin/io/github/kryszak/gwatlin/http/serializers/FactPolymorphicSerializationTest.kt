package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.Damage
import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.util.ResourcesUtil.readResource
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldEndWith
import io.kotest.matchers.types.beInstanceOf
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

internal class FactPolymorphicSerializationTest : ShouldSpec({

    context("Get item of type") {
        withData(
            mapOf(
                "Flesh golem" to FactTestInput("/responses/gamemechanics/skills/10646-necro-elite.json", Companion::necroEliteAssertion)
            )
        ) { input ->
            val skill = readSkillJson(input.res)
            input.test(skill)
            val jsonTxt = JsonConfigurer.json.encodeToString(skill)
            // Ensure all traited_facts objects are flat
            JsonConfigurer.json.parseToJsonElement(jsonTxt).jsonObject["traited_facts"]?.jsonArray?.forEach { traitedFact ->
                assertSoftly(traitedFact.jsonObject) {
                    // traitedFact required fields
                    keys shouldContain "requires_trait"
                    // fact required fields
                    keys shouldContain "type"
                }
            }
        }
    }
}) {
    private data class FactTestInput(
        val res: String,
        val test: (Skill) -> Unit,
    )

    companion object {
        fun necroEliteAssertion(skill: Skill) = assertSoftly(skill) {
            facts shouldHaveSize 4
            traitedFacts shouldHaveSize 2
            assertSoftly(traitedFacts[0]) {
                requiresTrait shouldBe 858
                overrides shouldBe 1
                fact should beInstanceOf<Damage>()
                assertSoftly(fact) {
                    text shouldBe "Damage"
                    icon shouldEndWith "/156657.png"
                }
            }
        }

        fun readSkillJson(res: String) = readResource(res)
            .let<_, Skill>(JsonConfigurer.json::decodeFromString)

    }
}
