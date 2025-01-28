package io.github.kryszak.e2e.story

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.story.GWBackstoryClient
import io.github.kryszak.gwatlin.api.story.GWStoryClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class StoryE2ETests : BaseE2ESpec() {
    init {
        context("Backstory") {
            val client = GWBackstoryClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch answers in $language language") {
                    shouldNotThrowAny { client.getBackstoryAnswers(language) }
                }
            }
            ApiLanguage.entries.forEach { language ->
                expect("Fetch questions in $language language") {
                    shouldNotThrowAny { client.getBackstoryQuestions(language) }
                }
            }
        }
        context("Story") {
            val client = GWStoryClient()
            ApiLanguage.entries.forEach { language ->
                expect("Fetch stories in $language language") {
                    shouldNotThrowAny { client.getStories(language) }
                }
            }
            ApiLanguage.entries.forEach { language ->
                expect("Fetch seasons in $language language") {
                    shouldNotThrowAny { client.getStorySeasons(language) }
                }
            }
        }
    }
}