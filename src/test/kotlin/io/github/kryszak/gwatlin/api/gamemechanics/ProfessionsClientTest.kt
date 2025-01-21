package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.gamemechanics.model.profession.TrainingCategory
import io.github.kryszak.gwatlin.api.gamemechanics.model.profession.TrainingTrackType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class ProfessionsClientTest : BaseWiremockTest() {

    private val professionsClient = GWProfessionsClient()

    init {
        should("Get profession ids") {
            // given
            stubResponse("/v2/professions", "/responses/gamemechanics/professions/profession_ids.json")

            // when
            val professionIds = professionsClient.getProfessionIds()

            // then
            professionIds shouldHaveSize 9
        }

        should("Get professions") {
            // given
            val ids = listOf("Engineer", "Warrior")
            val lang = ApiLanguage.EN

            stubResponse(
                "/v2/professions?ids=Engineer,Warrior",
                "/responses/gamemechanics/professions/professions.json",
                language = lang
            )

            // when
            val professions = professionsClient.getProfessions(ids, lang)

            // then
            assertSoftly(professions[0]) {
                id shouldBe "Engineer"
                name shouldBe "Engineer"
                icon shouldBe "https://render.guildwars2.com/file/5CCB361F44CCC7256132405D31E3A24DACCF440A/156632.png"
                iconBig shouldBe "https://render.guildwars2.com/file/A94D00911BD47CDE39A104F90C7D07DE623554ED/156631.png"
                specializations shouldHaveSize 7
                assertSoftly(weapons.hammer!!) {
                    specialization shouldBe 43
                    flags shouldHaveSize 1
                    skills shouldHaveSize 5
                }
                flags shouldHaveSize 1
                assertSoftly(training[0]) {
                    id shouldBe 21
                    category shouldBe TrainingCategory.SKILLS
                    name shouldBe "Elixir Training"
                    assertSoftly(track[0]) {
                        cost shouldBe 2
                        type shouldBe TrainingTrackType.SKILL
                        skillId shouldBe 5821
                    }
                }
            }
        }

        should("Get all professions") {
            // given
            val lang = ApiLanguage.EN

            stubResponse("/v2/professions?ids=all", "/responses/gamemechanics/professions/professions_all.json", language = lang)

            // when
            val professions = professionsClient.getAllProfessions(lang)

            // then
            professions shouldHaveSize 9
        }

        should("Throw exception on non existing profession") {
            // given
            val id = "asdf"

            stubResponse("/v2/professions?ids=asdf", "/responses/gamemechanics/professions/professions_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { professionsClient.getProfessions(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }
    }
}