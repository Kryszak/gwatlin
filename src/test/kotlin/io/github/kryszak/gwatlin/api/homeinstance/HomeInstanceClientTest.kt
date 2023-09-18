package io.github.kryszak.gwatlin.api.homeinstance

import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.api.homeinstance.model.Cat
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class HomeInstanceClientTest : BaseWiremockTest() {

    private val homeInstanceClient = GWHomeInstanceClient()

    init {
        should("Get cat ids list") {
            // given
            stubResponse("/home/cats", "/responses/homeinstance/cat_ids.json")

            // when
            val catIdsList = homeInstanceClient.getCatIds()

            // then
            catIdsList shouldHaveSize 35
        }

        should("Get cat") {
            // given
            val id = 1
            stubResponse("/home/cats/1", "/responses/homeinstance/cat.json")

            // when
            val cat = homeInstanceClient.getCat(id)

            // then
            assertSoftly(cat) {
                id shouldBe 1
                hint shouldBe "chicken"
            }
        }

        should("Throw exception on non existing cat") {
            // given
            val id = 100
            stubResponse("/home/cats/100", "/responses/homeinstance/cat_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { homeInstanceClient.getCat(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }

        should("Get cat list") {
            // given
            val ids = listOf(1, 2, 3)
            stubResponse("/home/cats?ids=1,2,3", "/responses/homeinstance/cats.json")

            // when
            val catList = homeInstanceClient.getCats(ids)

            // then
            catList shouldContainExactly listOf(
                Cat(id = 1, hint = "chicken"),
                Cat(id = 2, hint = "grilled_chicken"),
                Cat(id = 3, hint = "spicy_flank")
            )
        }

        should("Get node ids list") {
            // given
            stubResponse("/home/nodes", "/responses/homeinstance/node_ids.json")

            // when
            val nodeIdsList = homeInstanceClient.getNodeIds()

            // then
            nodeIdsList shouldHaveSize 50
        }
    }
}