package io.github.kryszak.e2e.achievement

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.e2e.randomElements
import io.github.kryszak.gwatlin.api.achievement.GWAchievementsClient
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class AchievementE2ETests : BaseE2ESpec() {
    private val client = GWAchievementsClient()

    init {
        context("Achievements") {
            expect("Random achievements are fetched correctly") {
                val achievementIds = client.getAchievementIdsList().randomElements(100)
                shouldNotThrowAny {
                    client.getAchievementsByIds(achievementIds)
                }
            }
            expect("Fetch paged achievements") {
                shouldNotThrowAny { client.getPagedAchievements(PageRequest(0, 10)) }
            }
            expect("Achievement categories is fetched correctly") {
                val categoryIds = client.getAchievementCategoryIds().randomElements(200)
                shouldNotThrowAny {
                    client.getAchievementCategories(categoryIds)
                }
            }
            expect("Fetch paged achievement categories") {
                shouldNotThrowAny { client.getPagedAchievementCategories(PageRequest(0, 10)) }
            }
            expect("Achievement groups is fetched correctly") {
                val groupIds = client.getAchievementGroupIds()
                shouldNotThrowAny {
                    client.getAchievementGroups(groupIds)
                }
            }
            expect("Fetch paged achievement groups") {
                shouldNotThrowAny { client.getPagedAchievementGroups(PageRequest(0, 10)) }
            }
        }
    }
}
