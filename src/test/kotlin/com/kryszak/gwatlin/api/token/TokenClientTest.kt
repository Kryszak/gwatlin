package com.kryszak.gwatlin.api.token

import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class TokenClientTest : BaseWiremockTest() {

    private val apiKey = "1234"

    private val tokenClient = GWTokenClient(apiKey)

    init {
        should("Get token info") {
            // given
            stubResponse("/tokeninfo", "/responses/token/token.json", apiKey = apiKey)

            // when
            val tokenInfo = tokenClient.getTokenInfo()

            // then
            assertSoftly(tokenInfo) {
                id shouldBe "ABCDE02B-8888-FEBA-1234-DE98765C7DEF"
                name shouldBe "My API Key"
                permissions shouldContainExactly listOf(
                    "account",
                    "characters",
                    "tradingpost",
                    "unlocks",
                    "build"
                )
            }
        }
    }
}