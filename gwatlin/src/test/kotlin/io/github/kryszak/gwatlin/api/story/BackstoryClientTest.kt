package io.github.kryszak.gwatlin.api.story

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

internal class BackstoryClientTest : BaseWiremockTest() {

    private val backstoryClient = GWBackstoryClient()

    init {
        should("Get backstory answers") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/backstory/answers?ids=all", "/responses/story/backstory_answers.json", language = lang)

            // when
            val answers = backstoryClient.getBackstoryAnswers(lang)

            // then
            assertSoftly(answers[0]) {
                id shouldBe "7-54"
                title shouldBe "Dignity"
                description shouldBe "I'm dignified even when up to my ears in mud. It's what makes people respect me. A serious, thoughtful demeanor is the route to success."
                journal shouldBe "Though trouble may follow me, I overcome it with dignity."
                question shouldBe 7
                races.shouldBeEmpty()
                professions.shouldBeEmpty()
            }
        }

        should("Get backstory questions") {
            // given
            val lang = ApiLanguage.EN
            stubResponse("/v2/backstory/questions?ids=all", "/responses/story/backstory_questions.json", language = lang)

            // when
            val questions = backstoryClient.getBackstoryQuestions(lang)

            // then
            assertSoftly(questions[0]) {
                id shouldBe 7
                title shouldBe "My Personality"
                description shouldBe "Trouble may follow me, but I use my ______ to overcome it."
                answers shouldContainExactly listOf("7-53", "7-54", "7-55")
                order shouldBe 1
                races.shouldBeEmpty()
                professions.shouldBeEmpty()
            }
        }
    }
}