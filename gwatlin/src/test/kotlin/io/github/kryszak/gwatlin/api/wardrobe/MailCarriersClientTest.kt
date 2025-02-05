package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class MailCarriersClientTest : BaseWiremockTest() {

    private val mailCarriersClient = GWMailCarriersClient()

    init {
        should("Get mail carrier ids") {
            // given
            stubResponse("/v2/mailcarriers", "/responses/wardrobe/mailcarrier/mailcarrier_ids.json")

            // when
            val mailCarrierIds = mailCarriersClient.getMailCarrierIds()

            // then
            mailCarrierIds shouldHaveSize 16
        }

        should("Get mail carrier") {
            // given
            val mailCarrierId = 1
            stubResponse("/v2/mailcarriers/$mailCarrierId", "/responses/wardrobe/mailcarrier/mailcarrier.json")

            // when
            val mailCarrier = mailCarriersClient.getMailCarrier(mailCarrierId)

            // then
            assertSoftly(mailCarrier) {
                id shouldBe mailCarrierId
                unlockItems shouldContainExactly listOf(68102)
                order shouldBe 4
                icon shouldBe "https://render.guildwars2.com/file/DC800626B873260155C528297325C07635FFD41E/924620.png"
                name shouldBe "Confetti Mail Delivery"
                flags.shouldBeEmpty()
            }
        }

        should("Get mail carriers") {
            // given
            val mailCarrierIds = listOf(2, 3)
            stubResponse(
                "/v2/mailcarriers?ids=${mailCarrierIds.joinToString(",")}",
                "/responses/wardrobe/mailcarrier/mailcarriers.json"
            )

            // when
            val mailCarriers = mailCarriersClient.getMailCarriers(mailCarrierIds)

            // then
            assertSoftly(mailCarriers) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe mailCarrierIds[0]
                    unlockItems shouldContainExactly listOf(68682)
                    order shouldBe 10
                    icon shouldBe "https://render.guildwars2.com/file/E7D86E3AD54E64AEDD9493E5D86C6919019C02B5/1001225.png"
                    name shouldBe "Sylvari Seed Pod Mail Carrier"
                    flags.shouldBeEmpty()
                }
                assertSoftly(it[1]) {
                    id shouldBe mailCarrierIds[1]
                    unlockItems shouldContainExactly listOf(68104)
                    order shouldBe 2
                    icon shouldBe "https://render.guildwars2.com/file/2A18167F02E75F1C49EB0C9ABC357E0A593FB573/919383.png"
                    name shouldBe "Gifts Mail Delivery"
                    flags.shouldBeEmpty()
                }
            }
        }

        should("Get paged mail carriers") {
            // given
            stubResponse(
                "/v2/mailcarriers?page=0&page_size=10",
                "/responses/wardrobe/mailcarrier/mailcarriers_paged.json",
                pageParams = PageParameters(10, 2, 10, 16)
            )

            // when
            val pagedMailCarriers = mailCarriersClient.getPagedMailCarriers(PageRequest(0, 10))

            // then
            assertSoftly(pagedMailCarriers) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 2
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 16
            }
        }
    }
}
