package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class PetsClientTest : BaseWiremockTest() {

    private val petsClient = GWPetsClient()

    init {
        should("Get pet ids list") {
            // given
            stubResponse("/v2/pets", "/responses/gamemechanics/pets/pet_ids.json")

            // when
            val petIds = petsClient.getPetIds()

            // then
            petIds shouldHaveSize 55
        }

        should("Get pets") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponse("/v2/pets?ids=1,2", "/responses/gamemechanics/pets/pets.json", language = lang)

            // when
            val pets = petsClient.getPets(ids, lang)

            // then
            assertSoftly(pets[0]) {
                id shouldBe 1
                name shouldBe "Juvenile Jungle Stalker"
                description shouldBe "Jungle stalkers rely on their power to take down prey. They enter battle with a mighty roar, inspiring allies as they lay into their victims with claws and teeth. They love tummy rubs. —Acht"
                icon shouldBe "https://render.guildwars2.com/file/EF1CBC60372CC60E420AD479A3504D90207A9A3F/52535.png"
                assertSoftly(skills[0]) {
                    id shouldBe 12658
                }
            }
        }

        should("Get all pets") {
            // given
            val lang = ApiLanguage.EN

            stubResponse("/v2/pets?ids=all", "/responses/gamemechanics/pets/pets_all.json", language = lang)

            // when
            val pets = petsClient.getAllPets(lang)

            // then
            pets shouldHaveSize 55
        }

        should("Get paged pets") {
            // given
            stubResponse(
                "/v2/pets?page=0&page_size=10",
                "/responses/gamemechanics/pets/pets_paged.json",
                pageParams = PageParameters(10, 7, 10, 63)
            )

            // when
            val pagedPets = petsClient.getPagedPets(PageRequest(0, 10))

            // then
            assertSoftly(pagedPets) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 7
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 63
            }
        }
    }
}