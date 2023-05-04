package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.BaseWiremockTest
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
            stubResponse("/specializations", "/responses/gamemechanics/specialization_ids.json")

            // when
            val specializationIds = specializationClient.getSpecializationIds()

            // then
            specializationIds shouldHaveSize 63
        }

        should("Get specialization") {
            // given
            val id = 1
            val lang = ApiLanguage.EN

            stubResponseWithLanguage("/specializations/1", "/responses/gamemechanics/specialization.json", lang)

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

            stubNotFoundResponse("/specializations/100", "/responses/gamemechanics/specialization_error.json")

            // when
            val exception = shouldThrow<ApiRequestException> { specializationClient.getSpecialization(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }
    }
}