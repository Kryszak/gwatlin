package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class RacesClientTest : BaseWiremockTest() {

    private val racesClient = GWRacesClient()

    init {
        should("Ghould get race ids") {
            // given
            stubResponse("/races", "/responses/gamemechanics/race_ids.json")

            // when
            val raceIds = racesClient.getRaceIds()

            // then
            raceIds shouldHaveSize 5
        }

        should("Ghould get race") {
            // given
            val id = "Asura"

            stubResponse("/races/Asura", "/responses/gamemechanics/race.json")

            // when
            val race = racesClient.getRace(id)

            // then
            assertSoftly(race) {
                id shouldBe "Asura"
                name shouldBe "Asura"
                skills shouldHaveSize 7
            }
        }

        should("Throw exception on non existing race") {
            // given
            val id = "nobody"

            stubResponse("/races/nobody", "/responses/gamemechanics/race_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { racesClient.getRace(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }
    }
}