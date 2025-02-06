package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class CurrencyClientTest : BaseWiremockTest() {

    private val currencyClient = GWCurrencyClient()

    init {
        should("Get all currencies") {
            // given
            val lang = ApiLanguage.EN
            stubResponse(
                "/v2/currencies?ids=all",
                "/responses/gamemechanics/currencies/currencies_all.json",
                language = lang
            )

            // when
            val currencies = currencyClient.getAllCurrencies(lang)

            // then
            assertSoftly(currencies[0]) {
                id shouldBe 1
                name shouldBe "Coin"
                description shouldBe "The primary currency of Tyria. Spent at vendors throughout the world."
                order shouldBe 101
                icon shouldBe "https://render.guildwars2.com/file/98457F504BA2FAC8457F532C4B30EDC23929ACF9/619316.png"
            }
        }
        should("Get currency ids") {
            // given
            stubResponse("/v2/currencies", "/responses/gamemechanics/currencies/currency_ids.json")

            // when
            val currencyIds = currencyClient.getCurrencyIds()

            // then
            currencyIds shouldHaveSize 73
        }
        should("Get currency") {
            // given
            val currencyId = 1
            stubResponse("/v2/currencies/$currencyId", "/responses/gamemechanics/currencies/currency.json")

            // when
            val currency = currencyClient.getCurrency(currencyId)

            // then
            assertSoftly(currency) {
                id shouldBe 1
                name shouldBe "Coin"
                description shouldBe "The primary currency of Tyria. Spent at vendors throughout the world."
                order shouldBe 101
                icon shouldBe "https://render.guildwars2.com/file/98457F504BA2FAC8457F532C4B30EDC23929ACF9/619316.png"
            }
        }
        should("Get currencies") {
            // given
            val currencyIds = listOf(1, 2)
            stubResponse("/v2/currencies?ids=${currencyIds.joinToString(",")}", "/responses/gamemechanics/currencies/currencies.json")

            // when
            val currencies = currencyClient.getCurrencies(currencyIds)

            // then
            assertSoftly(currencies) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe 2
                    name shouldBe "Karma"
                    description shouldBe "Earned through various activities. Spent at vendors throughout the world."
                    order shouldBe 102
                    icon shouldBe "https://render.guildwars2.com/file/94953FA23D3E0D23559624015DFEA4CFAA07F0E5/155026.png"
                }
                assertSoftly(it[1]) {
                    id shouldBe 3
                    name shouldBe "Laurel"
                    description shouldBe "Obtained from Wizard's Vault rewards. Used to purchase various rewards from laurel merchants."
                    order shouldBe 104
                    icon shouldBe "https://render.guildwars2.com/file/A1BD345AD9192C3A585BE2F6CB0617C5A797A1E2/619317.png"
                }
            }
        }
        should("Get paged currencies") {
            // given
            stubResponse(
                "/v2/currencies?page=0&page_size=10",
                "/responses/gamemechanics/currencies/currencies_paged.json",
                pageParams = PageParameters(10, 8, 10, 73)
            )

            // when
            val pagedCurrencies = currencyClient.getPagedCurrencies(PageRequest(0, 10))

            // then
            assertSoftly(pagedCurrencies) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 8
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 73
            }
        }
    }
}