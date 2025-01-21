package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.TraitSlot
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.TraitTier
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

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
            val lang = io.github.kryszak.gwatlin.api.ApiLanguage.EN

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
    }
}