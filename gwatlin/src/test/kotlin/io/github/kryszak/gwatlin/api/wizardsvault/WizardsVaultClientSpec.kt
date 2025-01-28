package io.github.kryszak.gwatlin.api.wizardsvault

import io.github.kryszak.gwatlin.api.shared.ListingType
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime

internal class WizardsVaultClientSpec : BaseWiremockTest() {

    private val client = GWWizardsVaultClient()

    init {
        should("Get season") {
            // given
            stubResponse("/v2/wizardsvault", "/responses/wizardsvault/season.json")

            // when
            val season = client.getSeason()

            // then
            assertSoftly(season) {
                title shouldBe "Godspawn Season"
                start shouldBe OffsetDateTime.parse("2024-10-19T16:00:00Z")
                end shouldBe OffsetDateTime.parse("2025-02-25T17:00:00Z")
                listings shouldHaveSize 57
                objectives shouldHaveSize 161
            }
        }

        should("Get listing ids") {
            // given
            stubResponse("/v2/wizardsvault/listings", "/responses/wizardsvault/listing/listing_ids.json")

            // when
            val listingIds = client.getListingIds()

            // then
            listingIds shouldHaveSize 57
        }

        should("Get listing") {
            // given
            val listingId = 1
            stubResponse("/v2/wizardsvault/listings/$listingId", "/responses/wizardsvault/listing/listing.json")

            // when
            val listing = client.getListing(listingId)

            // then
            assertSoftly(listing) {
                id shouldBe listingId
                itemId shouldBe 101828
                itemCount shouldBe 1
                type shouldBe ListingType.LEGACY
                cost shouldBe 1200
            }
        }

        should("Get listings") {
            // given
            val listingIds = listOf(2, 3)
            stubResponse(
                "/v2/wizardsvault/listings?ids=${listingIds.joinToString(",")}",
                "/responses/wizardsvault/listing/listings.json"
            )

            // when
            val listings = client.getListings(listingIds)

            // then
            assertSoftly(listings) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe listingIds[0]
                    itemId shouldBe 101933
                    itemCount shouldBe 1
                    type shouldBe ListingType.LEGACY
                    cost shouldBe 180
                }
                assertSoftly(it[1]) {
                    id shouldBe listingIds[1]
                    itemId shouldBe 101626
                    itemCount shouldBe 1
                    type shouldBe ListingType.LEGACY
                    cost shouldBe 450
                }
            }
        }

        should("Get objective ids") {
            // given
            stubResponse("/v2/wizardsvault/objectives", "/responses/wizardsvault/objective/objective_ids.json")

            // when
            val objectiveIds = client.getObjectiveIds()

            // then
            objectiveIds shouldHaveSize 161
        }

        should("Get objective") {
            // given
            val objectiveId = 1
            stubResponse("/v2/wizardsvault/objectives/$objectiveId", "/responses/wizardsvault/objective/objective.json")

            // when
            val objective = client.getObjective(objectiveId)

            // then
            assertSoftly(objective) {
                id shouldBe objectiveId
                title shouldBe "Complete 3 Events"
                track shouldBe "PvE"
                acclaim shouldBe 10
            }
        }

        should("Get objectives") {
            // given
            val objectiveIds = listOf(2, 3)
            stubResponse(
                "/v2/wizardsvault/objectives?ids=${objectiveIds.joinToString(",")}",
                "/responses/wizardsvault/objective/objectives.json"
            )

            // when
            val objectives = client.getObjectives(objectiveIds)

            // then
            assertSoftly(objectives) {
                it shouldHaveSize 2
                assertSoftly(it[0]) {
                    id shouldBe objectiveIds[0]
                    title shouldBe "Compete in 1 Player vs. Player Team Battle"
                    track shouldBe "PvP"
                    acclaim shouldBe 10
                }
                assertSoftly(it[1]) {
                    id shouldBe objectiveIds[1]
                    title shouldBe "Complete 5 Bounty Missions in Crystal Oasis or Group Events"
                    track shouldBe "PvE"
                    acclaim shouldBe 50
                }
            }
        }
    }
}
