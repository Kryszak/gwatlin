package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class GlidersClientTest : BaseWiremockTest() {

    private val glidersClient = GWGlidersClient()

    init {
        should("Get glider ids") {
            // given
            stubResponse("/v2/gliders", "/responses/wardrobe/gliders/glider_ids.json")

            // when
            val gliderIds = glidersClient.getGliderIds()

            // then
            gliderIds shouldHaveSize 136
        }

        should("Get glider") {
            // given
            val gliderId = 1
            stubResponse("/v2/gliders/$gliderId", "/responses/wardrobe/gliders/glider.json")

            // when
            val glider = glidersClient.getGlider(gliderId)

            // then
            assertSoftly(glider) {
                id shouldBe gliderId
                unlockItems shouldContainExactly listOf(70048)
                order shouldBe 3
                icon shouldBe "https://render.guildwars2.com/file/5978F84C08E69460B7FD34346745E7183562A35A/951784.png"
                name shouldBe "Black Feather Wings Glider"
                description shouldBe """<c=@reminder>This is only available from the Black Lion Trading Company during limited-time sales.</c>

If you wear the Black Feather Wings Backpack, speak to a scout in Verdant Brink to receive a free matching Glider."""
                defaultDyes shouldContainExactly listOf()
            }
        }

        should("Get gliders") {
            // given
            val gliderIds = listOf(3,4)
            stubResponse("/v2/gliders?ids=${gliderIds.joinToString(",")}", "/responses/wardrobe/gliders/gliders.json")

            // when
            val gliders = glidersClient.getGliders(gliderIds)

            // then
            assertSoftly(gliders) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe gliderIds[0]
                    unlockItems shouldContainExactly listOf(70009)
                    order shouldBe 3
                    icon shouldBe "https://render.guildwars2.com/file/FB9A14C04510096F4E33205AC2DE15BB07ADA0FF/1010543.png"
                    name shouldBe "White Feather Wings Glider"
                    description shouldBe """<c=@reminder>This is only available from the Black Lion Trading Company during limited-time sales.</c>

If you wear the White Feather Wings Backpack, speak to a scout in Verdant Brink to receive a free matching glider."""
                    defaultDyes shouldContainExactly listOf()
                }
                assertSoftly(it[1]) {
                    id shouldBe gliderIds[1]
                    unlockItems shouldContainExactly listOf(76236)
                    order shouldBe 10
                    icon shouldBe "https://render.guildwars2.com/file/60BAF50456D5D49E672D9CE3E7A34391E2CA8011/1200572.png"
                    name shouldBe "Exalted Glider"
                    description shouldBe "<c=@reminder>This is only available from the Black Lion Trading Company during limited-time sales.</c>"
                    defaultDyes shouldContainExactly listOf()
                }
            }
        }
    }
}
