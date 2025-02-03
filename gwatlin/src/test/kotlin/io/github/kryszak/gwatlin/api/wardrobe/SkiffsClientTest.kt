package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class SkiffsClientTest : BaseWiremockTest() {

    private val skiffsClient = GWSkiffsClient()

    init {
        should("Get skiff ids") {
            // given
            stubResponse("/v2/skiffs", "/responses/wardrobe/skiff/skiff_ids.json")

            // when
            val skiffIds = skiffsClient.getSkiffIds()

            // then
            skiffIds shouldHaveSize 19
        }

        should("Get skiff") {
            // given
            val skiffId = 410
            stubResponse("/v2/skiffs/$skiffId", "/responses/wardrobe/skiff/skiff.json")

            // when
            val skiff = skiffsClient.getSkiff(skiffId)

            // then
            assertSoftly(skiff) {
                id shouldBe skiffId
                name shouldBe "Fishing Skiff"
                icon shouldBe "https://render.guildwars2.com/file/BC08E6346DBFA290A4BE58AE073C9BA1029A57BB/2593817.png"
                assertSoftly(dyeSlots) {
                    it shouldHaveSize 1
                    assertSoftly(it[0]) {
                        colorId shouldBe 376
                        material shouldBe "metal"
                    }
                }
            }
        }

        should("Get skiffs") {
            // given
            val skiffIds = listOf(413, 420)
            stubResponse("/v2/skiffs?ids=${skiffIds.joinToString(",")}", "/responses/wardrobe/skiff/skiffs.json")

            // when
            val skiffs = skiffsClient.getSkiffs(skiffIds)

            // then
            assertSoftly(skiffs) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe skiffIds[0]
                    name shouldBe "Shing Jea Dragon Boat"
                    icon shouldBe "https://render.guildwars2.com/file/1055EEB4C9C4EEE1C8A23F08A7C6C303A01E14C0/2472117.png"
                    assertSoftly(dyeSlots) {
                        it shouldHaveSize 4
                        assertSoftly(it[0]) {
                            colorId shouldBe 1153
                            material shouldBe "metal"
                        }
                    }
                }
                assertSoftly(it[1]) {
                    id shouldBe skiffIds[1]
                    name shouldBe "Crescent Canoe"
                    icon shouldBe "https://render.guildwars2.com/file/0DBDEE02C192D0D417CA01F8532D736C332FF264/2660058.png"
                    assertSoftly(dyeSlots) {
                        it shouldHaveSize 4
                        assertSoftly(it[0]) {
                            colorId shouldBe 67
                            material shouldBe "metal"
                        }
                    }
                }
            }
        }
    }
}
