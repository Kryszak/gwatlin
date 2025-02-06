package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class SpecializationClientTest : BaseWiremockTest() {

    private val specializationClient = GWSpecializationClient()

    init {
        should("Get specialization ids") {
            // given
            stubResponse("/v2/specializations", "/responses/gamemechanics/specializations/specialization_ids.json")

            // when
            val specializationIds = specializationClient.getSpecializationIds()

            // then
            specializationIds shouldHaveSize 63
        }

        should("Get specialization") {
            // given
            val id = 1
            val lang = ApiLanguage.EN

            stubResponse("/v2/specializations/1", "/responses/gamemechanics/specializations/specialization.json", language = lang)

            // when
            val specialization = specializationClient.getSpecialization(id, lang)

            // then
            assertSoftly(specialization) {
                id shouldBe 1
                name shouldBe "Dueling"
                profession shouldBe "Mesmer"
                elite.shouldBeFalse()
                minorTraits shouldHaveSize 3
                majorTraits shouldHaveSize 9
                icon shouldBe "https://render.guildwars2.com/file/43C5400906A104C60F30DFE0A145D1E767353573/1012003.png"
                background shouldBe "https://render.guildwars2.com/file/992D53319C5FCD4AE841C592DC2AE91D5906AECF/1012057.png"
            }
        }

        should("Throw exception on non existing specialization") {
            // given
            val id = 100

            stubResponse("/v2/specializations/100", "/responses/gamemechanics/specializations/specialization_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { specializationClient.getSpecialization(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }

        should("Get specializations") {
            // given
            val ids = listOf(1,2,3)

            stubResponse("/v2/specializations?ids=${ids.joinToString(",")}", "/responses/gamemechanics/specializations/specializations.json")

            // when
            val specializations = specializationClient.getSpecializations(ids)

            // then
            assertSoftly(specializations) {
                it shouldHaveSize 3
                assertSoftly(it[0]) {
                    id shouldBe 1
                    name shouldBe "Dueling"
                    profession shouldBe "Mesmer"
                    elite.shouldBeFalse()
                    minorTraits shouldHaveSize 3
                    majorTraits shouldHaveSize 9
                    icon shouldBe "https://render.guildwars2.com/file/43C5400906A104C60F30DFE0A145D1E767353573/1012003.png"
                    background shouldBe "https://render.guildwars2.com/file/992D53319C5FCD4AE841C592DC2AE91D5906AECF/1012057.png"
                }
                assertSoftly(it[1]) {
                    id shouldBe 2
                    name shouldBe "Death Magic"
                    profession shouldBe "Necromancer"
                    elite.shouldBeFalse()
                    minorTraits shouldHaveSize 3
                    majorTraits shouldHaveSize 9
                    icon shouldBe "https://render.guildwars2.com/file/16663C1CDF532AB0DCC0CB08951DD2F49351D5D4/1012008.png"
                    background shouldBe "https://render.guildwars2.com/file/B3F92D581B0A036CABB51590E6E560B21708391F/1012067.png"
                }
                assertSoftly(it[2]) {
                    id shouldBe 3
                    name shouldBe "Invocation"
                    profession shouldBe "Revenant"
                    elite.shouldBeFalse()
                    minorTraits shouldHaveSize 3
                    majorTraits shouldHaveSize 9
                    icon shouldBe "https://render.guildwars2.com/file/2C4DCE5C0C255F32B51DCF9E4360106823EAF926/1012018.png"
                    background shouldBe "https://render.guildwars2.com/file/B73FB47165CB20DE21B1FAE91FE22BDE29B6FB76/1012093.png"
                }
            }
        }
        should("Get paged specializations") {
            // given
            stubResponse(
                "/v2/specializations?page=0&page_size=10",
                "/responses/gamemechanics/specializations/specializations_paged.json",
                pageParams = PageParameters(10, 8, 10, 72)
            )

            // when
            val pagedSpecializations = specializationClient.getPagedSpecializations(PageRequest(0, 10))

            // then
            assertSoftly(pagedSpecializations) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 8
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 72
            }
        }
    }
}