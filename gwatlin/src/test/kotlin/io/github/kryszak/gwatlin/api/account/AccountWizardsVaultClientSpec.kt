package io.github.kryszak.gwatlin.api.account

import io.github.kryszak.gwatlin.api.account.model.wizardsvault.ListingType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class AccountWizardsVaultClientSpec : BaseWiremockTest() {

    private val apiKey = "1234"

    private val wizardsVaultClient = GWAccountWizardsVaultClient(apiKey)

    init {
        should("Get listings") {
            // given
            stubResponse(
                "/v2/account/wizardsvault/listings",
                "/responses/account/wizardsvault/listings.json",
                apiKey = apiKey
            )

            // when
            val listings = wizardsVaultClient.getListings()

            // then
            assertSoftly(listings) {
                it shouldHaveSize 57
                assertSoftly(it[0]) {
                    id shouldBe 1
                    itemId shouldBe 101828
                    itemCount shouldBe 1
                    type shouldBe ListingType.LEGACY
                    cost shouldBe 1200
                    purchased shouldBe 0
                    purchaseLimit shouldBe 1
                }
                assertSoftly(it[23]) {
                    id shouldBe 24
                    itemId shouldBe 103789
                    itemCount shouldBe 1
                    type shouldBe ListingType.FEATURED
                    cost shouldBe 1000
                    purchased shouldBe 0
                    purchaseLimit shouldBe 1
                }
                assertSoftly(it[29]) {
                    id shouldBe 30
                    itemId shouldBe 103832
                    itemCount shouldBe 1
                    type shouldBe ListingType.NORMAL
                    cost shouldBe 1000
                    purchased shouldBe 0
                    purchaseLimit shouldBe 1
                }
            }
        }

        should("Get daily achievements") {
            // given
            stubResponse(
                "/v2/account/wizardsvault/daily",
                "/responses/account/wizardsvault/daily.json",
                apiKey = apiKey
            )

            // when
            val dailyAchievements = wizardsVaultClient.getDailyAchievements()

            // then
            assertSoftly(dailyAchievements) {
                metaProgressCurrent shouldBe 5
                metaProgressComplete shouldBe 4
                metaRewardItemId shouldBe 99961
                metaRewardAstral shouldBe 20
                metaRewardClaimed.shouldBeTrue()
                assertSoftly(objectives) {
                    it shouldHaveSize 5
                    assertSoftly(it[0]) {
                        id shouldBe 164
                        title shouldBe "Catch 5 Fish"
                        track shouldBe "PvE"
                        acclaim shouldBe 10
                        progressCurrent shouldBe 5
                        progressComplete shouldBe 5
                        claimed.shouldBeTrue()
                    }
                }
            }
        }

        should("Get weekly achievements") {
            // given
            stubResponse(
                "/v2/account/wizardsvault/weekly",
                "/responses/account/wizardsvault/weekly.json",
                apiKey = apiKey
            )

            // when
            val weeklyAchievements = wizardsVaultClient.getWeeklyAchievements()

            // then
            assertSoftly(weeklyAchievements) {
                metaProgressCurrent shouldBe 2
                metaProgressComplete shouldBe 6
                metaRewardItemId shouldBe 100137
                metaRewardAstral shouldBe 450
                metaRewardClaimed.shouldBeFalse()
                assertSoftly(objectives) {
                    it shouldHaveSize 8
                    assertSoftly(it[0]) {
                        id shouldBe 5
                        title shouldBe "Defeat 50 Veteran-Rank Enemies"
                        track shouldBe "PvE"
                        acclaim shouldBe 50
                        progressCurrent shouldBe 38
                        progressComplete shouldBe 50
                        claimed.shouldBeFalse()
                    }
                }
            }
        }

        should("Get special objectives") {
            // given
            stubResponse(
                "/v2/account/wizardsvault/special",
                "/responses/account/wizardsvault/special.json",
                apiKey = apiKey
            )

            // when
            val specialObjectives = wizardsVaultClient.getSpecialObjectives()

            // then
            assertSoftly(specialObjectives.objectives) {
                it shouldHaveSize 6
                assertSoftly(it[0]) {
                    id shouldBe 231
                    title shouldBe "Complete the Story Chapter Eventide's March"
                    track shouldBe "PvE"
                    acclaim shouldBe 500
                    progressCurrent shouldBe 0
                    progressComplete shouldBe 1
                    claimed.shouldBeFalse()
                }
            }
        }
    }
}
