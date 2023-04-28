package com.kryszak.gwatlin.api.achievement

import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.WiremockTestKt
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class AchievementsClientTest : WiremockTestKt() {

    private val achievementsClient = GWAchievementsClient()

    init {
        should("Get list of achievement ids") {
            // given
            stubResponse("/achievements", "/responses/achievements/achievement_ids.json")

            // when
            val idsList = achievementsClient.getAchievementIdsList()

            // then
            idsList.size shouldBe 3392
        }

        should("Get single achievement") {
            // given
            val id = 1840
            stubResponse("/achievements?ids=1840", "/responses/achievements/achievement.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(listOf(id))

            // then
            achievementList.size shouldBe 1
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

        should("Get list of achievements") {
            // given
            val ids = listOf(1840, 910, 2258)
            stubResponse("/achievements?ids=1840,910,2258", "/responses/achievements/achievement_list.json")

            // when
            val achievementList = achievementsClient.getAchievementsByIds(ids)

            // then
            achievementList.size shouldBe 3
        }

        should("Throw exception when non existing achievement is requested") {
            // given
            val id = 12345
            stubNotFoundResponse("/achievements?ids=12345", "/responses/achievements/achievement_error.json")

            // when
            val exception = shouldThrow<ApiRequestException> { achievementsClient.getAchievementsByIds(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }

        should("Get list of daily achievements") {
            // given
            stubResponse("/achievements/daily", "/responses/achievements/daily_achievements.json")

            // when
            val dailyAchievementList = achievementsClient.getDailyAchievements()

            // then
            assertSoftly(dailyAchievementList.pve[0]) {
                id shouldBe 1837
                level.min shouldBe 1
                level.max shouldBe 80
                requiredAccess shouldContainExactly listOf("GuildWars2", "HeartOfThorns", "PathOfFire")
            }
        }

        should("Get list of tomorrow daily achievements") {
            // given
            stubResponse("/achievements/daily/tomorrow", "/responses/achievements/daily_tomorrow_achievements.json")

            // when
            val tomorrowDailyAchievementList = achievementsClient.getDailyTomorrowAchievements()

            // then
            assertSoftly(tomorrowDailyAchievementList.pve[0]) {
                id shouldBe 3880
                level.min shouldBe 80
                level.max shouldBe 80
                requiredAccess shouldContainExactly listOf("GuildWars2", "HeartOfThorns", "PathOfFire")
            }
        }

        should("Get list of achievement group ids") {
            // given
            stubResponse("/achievements/groups", "/responses/achievements/achievement_group_ids.json")

            // when
            val achievementGroupIdsList = achievementsClient.getAchievementGroupIds()

            // then
            achievementGroupIdsList.size shouldBe 11
        }

        should("Get achievement group") {
            // given
            val id = "65B4B678-607E-4D97-B458-076C3E96A810"
            stubResponse(
                "/achievements/groups/65B4B678-607E-4D97-B458-076C3E96A810",
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
                categories.size shouldBe 6
            }
        }

        should("Get achievement category ids") {
            // given
            stubResponse("/achievements/categories", "/responses/achievements/achievement_categories.json")

            // when
            val achievementCategoryIdsList = achievementsClient.getAchievementCategoryIds()

            // then
            achievementCategoryIdsList.size shouldBe 196
        }

        should("Get achievement category") {
            // given
            val id = 1
            stubResponse("/achievements/categories/1", "/responses/achievements/achievement_category.json")

            // when
            val achievementCategory = achievementsClient.getAchievementCategory(id)

            // then
            assertSoftly(achievementCategory) {
                id shouldBe 1
                name shouldBe "Slayer"
                description shouldBe ""
                order shouldBe 10
                icon shouldBe "https://render.guildwars2.com/file/E00460A2CAD85D47406EAB4213D1010B3E80C9B0/42675.png"
                achievements.size shouldBe 44
            }
        }

        should("Throw exception on non existing category") {
            // given
            val id = 1000
            stubNotFoundResponse(
                "/achievements/categories/1000",
                "/responses/achievements/achievement_category_error.json"
            )

            // when
            val exception = shouldThrow<ApiRequestException> { achievementsClient.getAchievementCategory(id) }

            // then
            exception.message shouldBe "RetrieveError(text=no such id)"
        }
    }
}