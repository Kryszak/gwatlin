package io.github.kryszak.gwatlin.api.shared

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class PageRequestTest : ShouldSpec({
    should("Map itself to query parameters correctly") {
        // given
        val pageRequest = PageRequest(0, 10)

        // when
        val params = pageRequest.toQueryParams()

        // then
        params shouldBe "page=0&page_size=10"
    }
})
