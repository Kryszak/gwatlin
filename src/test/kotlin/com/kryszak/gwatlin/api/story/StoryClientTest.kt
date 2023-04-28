package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

internal class StoryClientTest : WiremockTest() {

    private val storyClient = GWStoryClient()

    init {
        should("Get stories") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/stories?ids=all", "/responses/story/stories.json", lang)

            // when
            val stories = storyClient.getStories(lang)

            // then
            assertSoftly(stories[0]) {
                id shouldBe 1
                season shouldBe "215AAA0F-CDAC-4F93-86DA-C155A99B5784"
                name shouldBe "My Story"
                description shouldBe ""
                timeline shouldBe "1325 AE"
                level shouldBe 1
                races shouldContainExactly listOf("Charr")
                order shouldBe 0
                professions.shouldBeNull()
                assertSoftly(chapters[0]) {
                    name shouldBe "1. Getting the Band Back Together"
                }
            }
        }

        should("Get story seasons") {
            // given
            val lang = ApiLanguage.EN
            stubResponseWithLanguage("/stories/seasons?ids=all", "/responses/story/story_seasons.json", lang)

            // when
            val seasons = storyClient.getStorySeasons(lang)

            // then
            assertSoftly(seasons[0]) {
                id shouldBe "09766A86-D88D-4DF2-9385-259E9A8CA583"
                name shouldBe "Living World Season 3"
                order shouldBe 5
                stories shouldContainExactly listOf(63, 65, 66, 56, 46, 64)
            }
        }
    }
}