package io.github.kryszak.gwatlin.api.achievement

import io.github.kryszak.gwatlin.api.exception.ApiRequestException
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class AchievementsClientTest : BaseWiremockTest() {

    private val achievementsClient = GWAchievementsClient()

    init {
        should("Get list of achievement ids") {
            // given
            stubResponse("/v2/achievements", "/responses/achievements/achievement_ids.json")

            // when
            val idsList = achievementsClient.getAchievementIdsList()

            // then
            idsList shouldHaveSize 3392
        }

        should("Get single achievement") {
            // given
            val id = 1840
            stubResponse("/v2/achievements?ids=1840", "/responses/achievements/achievement.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(listOf(id))

            // then
            achievementList shouldHaveSize 1
            assertSoftly(achievementList[0]) {
                id shouldBe 1840
                name shouldBe "Daily Completionist"
                description shouldBe ""
                requirement shouldBe "Complete any  PvE, WvW, or PvP Daily Achievements."
                lockedText shouldBe ""
                type shouldBe "Default"
                flags shouldContainExactly listOf("Pvp", "CategoryDisplay", "Daily")
                assertSoftly(tiers[0]) {
                    count shouldBe 3
                    points shouldBe 10
                }
                assertSoftly(rewards[0]) {
                    type shouldBe "Item"
                    count shouldBe 1
                }
            }
        }

        should("Get 3147: Walking on Fire") {
            // given
            val id = 3147
            stubResponse("/v2/achievements?ids=3147", "/responses/achievements/3147-walking-on-fire.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(listOf(id))

            // then
            achievementList shouldHaveSize 1
            assertSoftly(achievementList[0]) {
                this.id shouldBe 3147
                name shouldBe "Walking on Fire"
                rewards shouldHaveSize 0
            }
        }

        should("Get 5585: Dragon Ice Infuser") {
            // given
            val id = 5585
            stubResponse("/v2/achievements?ids=5585", "/responses/achievements/5585-dragon-ice-infuser.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(listOf(id))

            // then
            achievementList shouldHaveSize 1
            assertSoftly(achievementList[0]) {
                this.id shouldBe 5585
                name shouldBe "Dragon Ice Infuser"
                bits shouldHaveSize 16
                bits.distinct() shouldHaveSize 1
                assertSoftly(bits[0]) {
                    type.shouldBeNull()
                    this.id.shouldBeNull()
                    text.shouldBeNull()
                }
            }
        }

        should("Get list of achievements") {
            // given
            val ids = listOf(1840, 910, 2258)
            stubResponse("/v2/achievements?ids=1840,910,2258", "/responses/achievements/achievement_list.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(ids)

            // then
            achievementList shouldHaveSize 3
        }

        should("Throw exception when non existing achievement is requested") {
            // given
            val id = 12345
            stubResponse(
                "/v2/achievements?ids=12345",
                "/responses/achievements/achievement_error.json",
                responseStatus = 404
            )

            // when
            val exception = shouldThrow<ApiRequestException> { achievementsClient.getAchievementsByIds(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }

        should("Get list of achievement group ids") {
            // given
            stubResponse("/v2/achievements/groups", "/responses/achievements/achievement_group_ids.json")

            // when
            val achievementGroupIdsList = achievementsClient.getAchievementGroupIds()

            // then
            achievementGroupIdsList shouldHaveSize 11
        }

        should("Get achievement group") {
            // given
            val id = "65B4B678-607E-4D97-B458-076C3E96A810"
            stubResponse(
                "/v2/achievements/groups/65B4B678-607E-4D97-B458-076C3E96A810",
                "/responses/achievements/achievement_group.json"
            )

            // when
            val achievementGroup = achievementsClient.getAchievementGroup(id)

            // then
            assertSoftly(achievementGroup) {
                id shouldBe "65B4B678-607E-4D97-B458-076C3E96A810"
                name shouldBe "Heart of Thorns"
                description shouldBe "Achievements for accomplishments throughout the jungle."
                order shouldBe 6
                categories shouldHaveSize 6
            }
        }

        should("Get list of achievement groups") {
            // given
            val ids = listOf(
                "4E6A6CE7-B131-40BB-81A3-235CDBACDAA9",
                "A9F7378E-9C8A-48CC-9505-3094E661D5F6",
                "BE8B9954-5B55-4FCB-9022-B871AD00EAAB"
            )
            stubResponse(
                "/v2/achievements/groups?ids=${ids.joinToString(",")}",
                "/responses/achievements/achievement_groups.json"
            )

            // when
            val achievementGroups = achievementsClient.getAchievementGroups(ids)

            // then
            assertSoftly(achievementGroups) {
                it shouldHaveSize 3
                assertSoftly(it[0]) {
                    id shouldBe "4E6A6CE7-B131-40BB-81A3-235CDBACDAA9"
                    name shouldBe "Fractals of the Mists"
                    description shouldBe "Achievements related to the Fractals of the Mists."
                    order shouldBe 23
                    categories shouldHaveSize 7
                }
                assertSoftly(it[1]) {
                    id shouldBe "A9F7378E-9C8A-48CC-9505-3094E661D5F6"
                    name shouldBe "Historical"
                    description shouldBe "Completed achievements from previous updates."
                    order shouldBe 99
                    categories shouldHaveSize 64
                }
                assertSoftly(it[2]) {
                    id shouldBe "BE8B9954-5B55-4FCB-9022-B871AD00EAAB"
                    name shouldBe "Player vs. Player"
                    description shouldBe "Achievements related to PvP and activities."
                    order shouldBe 21
                    categories shouldHaveSize 23
                }
            }
        }

        should("Get achievement category ids") {
            // given
            stubResponse("/v2/achievements/categories", "/responses/achievements/achievement_category_ids.json")

            // when
            val achievementCategoryIdsList = achievementsClient.getAchievementCategoryIds()

            // then
            achievementCategoryIdsList shouldHaveSize 196
        }

        should("Get achievement category") {
            // given
            val id = 1
            stubResponse("/v2/achievements/categories/1", "/responses/achievements/achievement_category.json")

            // when
            val achievementCategory = achievementsClient.getAchievementCategory(id)

            // then
            assertSoftly(achievementCategory) {
                id shouldBe 1
                name shouldBe "Slayer"
                description shouldBe ""
                order shouldBe 10
                icon shouldBe "https://render.guildwars2.com/file/E00460A2CAD85D47406EAB4213D1010B3E80C9B0/42675.png"
                achievements shouldHaveSize 44
            }
        }

        should("Get achievement categories") {
            // given
            val ids = listOf(5, 6, 7)
            stubResponse(
                "/v2/achievements/categories?ids=${ids.joinToString(",")}",
                "/responses/achievements/achievement_categories.json"
            )

            // when
            val achievementCategories = achievementsClient.getAchievementCategories(ids)

            // then
            assertSoftly(achievementCategories) {
                it shouldHaveSize 3
                assertSoftly(it[0]) {
                    id shouldBe 5
                    name shouldBe "Explorer"
                    description shouldBe ""
                    order shouldBe 13
                    icon shouldBe "https://render.guildwars2.com/file/09F0A535AD957D00BBAE701AC50ABCDD7B792400/42680.png"
                    achievements shouldHaveSize 66
                }
                assertSoftly(it[1]) {
                    id shouldBe 6
                    name shouldBe "Fashion"
                    description shouldBe ""
                    order shouldBe 14
                    icon shouldBe "https://render.guildwars2.com/file/7FCDBD1110A3F8A3172541ED05664165C4A7B918/42683.png"
                    achievements shouldHaveSize 8
                }
                assertSoftly(it[2]) {
                    id shouldBe 7
                    name shouldBe "Weapon Master"
                    description shouldBe ""
                    order shouldBe 15
                    icon shouldBe "https://render.guildwars2.com/file/E57F44931D5D1C0DEB16A27803A4744492B834E2/42682.png"
                    achievements shouldHaveSize 20
                }
            }
        }

        should("Throw exception on non existing category") {
            // given
            val id = 1000
            stubResponse(
                "/v2/achievements/categories/1000",
                "/responses/achievements/achievement_category_error.json",
                responseStatus = 404
            )

            // when
            val exception = shouldThrow<ApiRequestException> { achievementsClient.getAchievementCategory(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }
    }
}