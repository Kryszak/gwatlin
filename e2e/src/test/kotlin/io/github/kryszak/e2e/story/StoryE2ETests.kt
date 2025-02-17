package io.github.kryszak.e2e.story

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.story.GWBackstoryClient
import io.github.kryszak.gwatlin.api.story.GWStoryClient
import io.kotest.assertions.throwables.shouldNotThrowAny

internal class StoryE2ETests : BaseE2ESpec() {
    init {
        ApiLanguage.entries.forEach { language ->
            context("$language language") {
                context("Backstory") {
                    val client = GWBackstoryClient()
                    expect("Fetch answers") {
                        shouldNotThrowAny { client.getBackstoryAnswers(language) }
                    }
                    expect("Fetch questions") {
                        shouldNotThrowAny { client.getBackstoryQuestions(language) }
                    }
                }
                context("Story") {
                    val client = GWStoryClient()
                    expect("Fetch stories") {
                        shouldNotThrowAny { client.getStories(language) }
                    }
                    expect("Fetch seasons") {
                        shouldNotThrowAny { client.getStorySeasons(language) }
                    }
                }
            }
        }
    }
}