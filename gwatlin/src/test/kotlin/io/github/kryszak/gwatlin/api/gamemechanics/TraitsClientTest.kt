package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.PrefixedBuff
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.TraitSlot
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.TraitTier
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

internal class TraitsClientTest : BaseWiremockTest() {

    private val traitsClient = GWTraitsClient()

    init {
        should("Get trait ids") {
            // given
            stubResponse("/v2/traits", "/responses/gamemechanics/traits/trait_ids.json")

            // when
            val traitIds = traitsClient.getTraitIds()

            // then
            traitIds shouldHaveSize 774
        }

        should("Get traits") {
            // given
            val ids = listOf(214, 221)
            val lang = ApiLanguage.EN

            stubResponse("/v2/traits?ids=214,221", "/responses/gamemechanics/traits/traits.json", language = lang)

            // when
            val traits = traitsClient.getTraits(ids, lang)

            // then
            assertSoftly(traits[0]) {
                id shouldBe 214
                tier shouldBe TraitTier.MASTER
                order shouldBe 1
                name shouldBe "Raging Storm"
                description shouldBe "Critically striking a foe grants fury. Gain ferocity while under the effects of fury."
                slot shouldBe TraitSlot.MAJOR
                specialization shouldBe 41
                icon shouldBe "https://render.guildwars2.com/file/74A414B378B54431EF183A37DA37CCFFFC0E04BD/2175040.png"
                assertSoftly(facts[0]) {
                    text shouldBe "Recharge"
                    type shouldBe "Recharge"
                    icon shouldBe "https://render.guildwars2.com/file/D767B963D120F077C3B163A05DC05A7317D7DB70/156651.png"
                }
            }
        }

        should("Get trait with PrefixedBuff fact") {
            // given
            val traitId = 229
            stubResponse("/v2/traits/$traitId", "/responses/gamemechanics/traits/229_trait_with_prefixedbuff.json")

            // when
            val trait = traitsClient.getTrait(traitId)

            // then
            assertSoftly(trait.facts[1]) {
                shouldBeInstanceOf<PrefixedBuff>()
                text shouldBe "Apply Buff/Condition"
                type shouldBe "PrefixedBuff"
                icon shouldBe "https://render.guildwars2.com/file/2FA9DF9D6BC17839BBEA14723F1C53D645DDB5E1/102852.png"
                duration shouldBe 10
                status shouldBe "Might"
                description shouldBe "Increased outgoing damage; stacks intensity."
                applyCount shouldBe 1
                assertSoftly(prefix) {
                    text shouldBe "Apply Buff/Condition"
                    icon shouldBe "https://render.guildwars2.com/file/1C91E9C799469ACC6EAF1ACD4B0AD8ACAB0C69A2/103035.png"
                    status shouldBe "Fire Attunement"
                    description shouldBe "Cast fire spells."
                }
            }
        }

        should("Get trait with skills") {
            // given
            val traitId = 238
            stubResponse("/v2/traits/$traitId", "/responses/gamemechanics/traits/238_evasive_arcana.json")

            // when
            val trait = traitsClient.getTrait(traitId)

            // then
            assertSoftly(trait.skills[0]) {
                name shouldBe "Flame Burst"
                description shouldBe "Burn nearby foes."
                icon shouldBe "https://render.guildwars2.com/file/610424237E18EA90F0D824454104355309FF5DDA/102959.png"
                id shouldBe 5794
                chatLink shouldBe "[&BqIWAAA=]"
                facts shouldHaveSize 5
            }
        }
    }
}