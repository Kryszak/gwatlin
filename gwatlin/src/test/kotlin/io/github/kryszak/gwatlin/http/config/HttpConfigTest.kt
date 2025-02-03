package io.github.kryszak.gwatlin.http.config

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal class HttpConfigTest : ShouldSpec({
    should("Load http configuration") {
        // given
        val config = HttpConfig()

        // expect
        assertSoftly(config) {
            baseUrl shouldBe "http://localhost:8089/v2"
            connectTimeout shouldBe 1000
            readTimeout shouldBe 15000
        }
    }
})