package io.github.kryszak.e2e.story

import io.github.kryszak.e2e.BaseE2ESpec
import io.github.kryszak.gwatlin.api.story.GWBackstoryClient
import io.github.kryszak.gwatlin.api.story.GWStoryClient
import io.kotest.assertions.throwables.shouldNotThrowAny

class StoryE2ETests : BaseE2ESpec() {
    init {
        context("Backstory") {
            val client = GWBackstoryClient()
            expect("Fetch answers") {
                shouldNotThrowAny { client.getBackstoryAnswers() }
            }
            expect("Fetch questions") {
                shouldNotThrowAny { client.getBackstoryQuestions() }
            }
        }
        context("Story") {
            val client = GWStoryClient()
            expect("Fetch stories") {
                shouldNotThrowAny { client.getStories() }
            }
            expect("Fetch seasons") {
                shouldNotThrowAny { client.getStorySeasons() }
            }
        }
    }
}