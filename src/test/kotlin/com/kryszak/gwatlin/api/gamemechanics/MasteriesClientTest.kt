package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe

internal class MasteriesClientTest : BaseWiremockTest() {

    private val gameMechanicsClient = GWMasteriesClient()

    init {
        should("Get masteries ids") {
            // given
            stubResponse("/masteries", "/responses/gamemechanics/masteries_ids.json")

            // when
            val idsList = gameMechanicsClient.getMasteriesIds()

            // then
            idsList shouldHaveSize 17
        }


        should("Get mastery in for (data.forAll)") {
            forAll(
                table(
                    headers("language", "expectedMastery", "stub"),
                    row(ApiLanguage.EN, englishMastery(), stubEnglishMasteryResponse()),
                    row(ApiLanguage.FR, frenchMastery(), stubFrenchMasteryResponse())
                )
            ) { language, expectedMastery, stubbing ->
                // given
                val id = 1
                stubbing()

                // when
                val mastery = gameMechanicsClient.getMastery(id, language)

                // then
                assertSoftly(mastery) {

                }
                mastery.shouldBeEqualToIgnoringFields(expectedMastery, Mastery::levels)
            }
        }

        should("Throw exception on non existing mastery") {
            // given
            val id = 40

            stubNotFoundResponse("/masteries/40", "/responses/gamemechanics/mastery_error.json")

            // when
            val exception = shouldThrow<ApiRequestException> { gameMechanicsClient.getMastery(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }

        should("Get list of masteries") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/masteries?ids=1,2", "/responses/gamemechanics/masteries.json", lang)

            // when
            val masteries = gameMechanicsClient.getMasteries(ids, lang)

            // then
            assertSoftly(masteries[0]) {
                id shouldBe 1
                name shouldBe "Exalted Lore"
                requirement shouldBe "Journey to Auric Basin to unlock the Exalted Lore Mastery track."
                order shouldBe 2
                background shouldBe "https://render.guildwars2.com/file/4E09B60E16E6A7404B0638A00D0C6A02F7294308/1228720.png"
                region shouldBe "Maguuma"
                assertSoftly(levels[0]) {
                    name shouldBe "Exalted Markings"
                    description shouldBe "Gain the knowledge to read Exalted markings. You can now decipher their words and gain access to secrets of their civilization."
                    instruction shouldBe "You can now interact with Exalted artifacts found in Auric Basin and the greater Maguuma Jungle."
                    icon shouldBe "https://render.guildwars2.com/file/7372DCB5085D75F672B50CB8F3577373B8F90468/1228654.png"
                    pointCost shouldBe 1
                    expCost shouldBe 508000
                }
            }
        }

        should("Get all masteries") {
            // given
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/masteries?ids=all", "/responses/gamemechanics/masteries_all.json", lang)

            // when
            val masteries = gameMechanicsClient.getAllMasteries(lang)

            // then
            masteries shouldHaveSize 17
        }
    }

    private fun stubFrenchMasteryResponse(): () -> Unit = {
        stubResponseWithLanguage(
            "/masteries/1",
            "/responses/gamemechanics/mastery_fr.json",
            ApiLanguage.FR
        )
    }

    private fun stubEnglishMasteryResponse(): () -> Unit = {
        stubResponseWithLanguage(
            "/masteries/1",
            "/responses/gamemechanics/mastery.json",
            ApiLanguage.EN
        )
    }

    private fun frenchMastery() = Mastery(
        1,
        "Ethnologie des Exaltés",
        "Rendez-vous dans le Bassin aurique pour déverrouiller le parcours de maîtrise Ethnologie des Exaltés.",
        2,
        "https://render.guildwars2.com/file/4E09B60E16E6A7404B0638A00D0C6A02F7294308/1228720.png",
        "Maguuma",
        listOf()
    )

    private fun englishMastery() = Mastery(
        1,
        "Exalted Lore",
        "Journey to Auric Basin to unlock the Exalted Lore Mastery track.",
        2,
        "https://render.guildwars2.com/file/4E09B60E16E6A7404B0638A00D0C6A02F7294308/1228720.png",
        "Maguuma",
        listOf()
    )
}