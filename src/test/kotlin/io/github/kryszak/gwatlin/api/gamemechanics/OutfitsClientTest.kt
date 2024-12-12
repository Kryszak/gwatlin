package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class OutfitsClientTest : BaseWiremockTest() {

    private val outfitsClient = GWOutfitsClient()

    init {
        should("Get outfits ids") {
            // given
            stubResponse("/v2/outfits", "/responses/gamemechanics/outfit_ids.json")

            // when
            val idsList = outfitsClient.getOutfitsIds()

            // then
            idsList shouldHaveSize 91
        }

        should("Get outfits") {
            // given
            val ids = listOf(1, 2)
            val lang = io.github.kryszak.gwatlin.api.ApiLanguage.EN

            stubResponse("/v2/outfits?ids=1,2", "/responses/gamemechanics/outfits.json", language = lang)

            // when
            val outfits = outfitsClient.getOutfits(ids, lang)

            // then
            assertSoftly(outfits[0]) {
                id shouldBe 1
                name shouldBe "Cook's Outfit"
                icon shouldBe "https://render.guildwars2.com/file/1509D1B76FCECC111E28D2F50EBEAD5DA102995A/340522.png"
                unlockItems shouldHaveSize 1
            }
        }

        should("Throw error on non existing outfit") {
            // given
            val id = 1000

            stubResponse("/v2/outfits?ids=1000", "/responses/gamemechanics/outfit_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { outfitsClient.getOutfits(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }

        should("Get all outfits") {
            // given
            val lang = io.github.kryszak.gwatlin.api.ApiLanguage.EN

            stubResponse("/v2/outfits?ids=all", "/responses/gamemechanics/outfits_all.json", language = lang)

            // when
            val outfits = outfitsClient.getAllOutfits(lang)

            // then
            outfits shouldHaveSize 91
        }
    }
}