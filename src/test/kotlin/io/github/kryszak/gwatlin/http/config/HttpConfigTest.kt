package io.github.kryszak.gwatlin.http.config

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

internal class HttpConfigTest : ShouldSpec({
    should("Load http configuration") {
        // given
        val config = HttpConfig()

        // expect
        config.baseUrl shouldBe "http://localhost:8089"
    }
})