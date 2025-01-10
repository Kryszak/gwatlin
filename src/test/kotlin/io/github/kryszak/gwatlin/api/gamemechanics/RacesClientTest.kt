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
        should("Should get race ids") {
            // given
            stubResponse("/v2/races", "/responses/gamemechanics/race_ids.json")

            // when
            val raceIds = racesClient.getRaceIds()

            // then
            raceIds shouldHaveSize 5
        }

        should("Should get race") {
            // given
            val id = "Asura"

            stubResponse("/v2/races/Asura", "/responses/gamemechanics/race.json")

            // when
            val race = racesClient.getRace(id)

            // then
            assertSoftly(race) {
                id shouldBe "Asura"
                name shouldBe "Asura"
                skills shouldHaveSize 7
            }
        }

        should("Get races") {
            // given
            val ids = listOf("Human", "Sylvari", "Norn")

            stubResponse("/v2/races?ids=${ids.joinToString(",")}", "/responses/gamemechanics/races.json")

            // when
            val races = racesClient.getRaces(ids)

            // then
            assertSoftly(races) {
                it shouldHaveSize 3
                assertSoftly(it[0]) {
                    id shouldBe "Human"
                    name shouldBe "Human"
                    skills shouldHaveSize 7
                }
                assertSoftly(it[1]) {
                    id shouldBe "Sylvari"
                    name shouldBe "Sylvari"
                    skills shouldHaveSize 7
                }
                assertSoftly(it[2]) {
                    id shouldBe "Norn"
                    name shouldBe "Norn"
                    skills shouldHaveSize 7
                }
            }
        }

        should("Throw exception on non existing race") {
            // given
            val id = "nobody"

            stubResponse("/v2/races/nobody", "/responses/gamemechanics/race_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { racesClient.getRace(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }
    }
}