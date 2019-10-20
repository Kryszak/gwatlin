package com.kryszak.gwatlin.clients.achievements

import com.kryszak.gwatlin.config.WiremockConfig

import static com.github.tomakehurst.wiremock.client.WireMock.*

class AchievementStubs extends WiremockConfig {

    def stubAchievementIdsResponse() {
        stubFor(
                get(urlEqualTo("/achievements"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_ids.json")))
        )
    }

    def stubAchievementSingleResponse() {
        stubFor(
                get(urlEqualTo("/achievements?ids=1840"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement.json")))
        )
    }

    def stubAchievementListResponse() {
        stubFor(
                get(urlEqualTo("/achievements?ids=1840,910,2258"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_list.json")))
        )
    }

    def stubAchievementListErrorResponse() {
        stubFor(
                get(urlEqualTo("/achievements?ids=12345"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/achievements/achievement_error.json")))
        )
    }

    def stubDailyAchievementListResponse() {
        stubFor(
                get(urlEqualTo("/achievements/daily"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/daily_achievements.json")))
        )
    }

    def stubDailyTomorrowAchievementListResponse() {
        stubFor(
                get(urlEqualTo("/achievements/daily/tomorrow"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/daily_tomorrow_achievements.json")))
        )
    }

    def stubAchievementGroupIdsResponse() {
        stubFor(
                get(urlEqualTo("/achievements/groups"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_group_ids.json")))
        )
    }

    def stubAchievementGroupResponse() {
        stubFor(
                get(urlEqualTo("/achievements/groups/65B4B678-607E-4D97-B458-076C3E96A810"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_group.json")))
        )
    }

    def stubAchievementCategoryIdsResponse() {
        stubFor(
                get(urlEqualTo("/achievements/categories"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_categories.json")))
        )
    }

    def stubAchievementCategoryResponse() {
        stubFor(
                get(urlEqualTo("/achievements/categories/1"))
                        .willReturn(okJson(parseResponseText("/responses/achievements/achievement_category.json")))
        )
    }

    def stubAchievementCategoryErrorResponse() {
        stubFor(
                get(urlEqualTo("/achievements/categories/1000"))
                        .willReturn(notFound().withBody(parseResponseText("/responses/achievements/achievement_category_error.json")))
        )
    }
}
