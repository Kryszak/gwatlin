package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe

internal class CurrencyClientSpec : BaseWiremockTest() {

    private val currencyClient = GWCurrencyClient()

    init {
        should("Get currencies") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/currencies?ids=all", "/responses/gamemechanics/currencies.json", language = lang)

            // when
            val currencies = currencyClient.getCurrencies(lang)

            // then
            assertSoftly(currencies[0]) {
                id shouldBe 1
                name shouldBe "Coin"
                description shouldBe "The primary currency of Tyria. Spent at vendors throughout the world."
                order shouldBe 101
                icon shouldBe "https://render.guildwars2.com/file/98457F504BA2FAC8457F532C4B30EDC23929ACF9/619316.png"
            }
        }
    }
}