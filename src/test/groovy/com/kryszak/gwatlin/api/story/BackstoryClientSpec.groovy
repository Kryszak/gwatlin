package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class BackstoryClientSpec extends WiremockTest {

    @Subject
    def backstoryClient = new GWBackstoryClient()

    def "Should get backstory answers"() {
        given: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/backstory/answers?ids=all", "/responses/story/backstory_answers.json", lang)

        when: "Requesting backstory answers"
        def answers = backstoryClient.getBackstoryAnswers(lang)

        then: "Retrieved answers match expected"
        verifyAll(answers.get(0)) {
            id == "7-54"
            title == "Dignity"
            description == "I'm dignified even when up to my ears in mud. It's what makes people respect me. A serious, thoughtful demeanor is the route to success."
            journal == "Though trouble may follow me, I overcome it with dignity."
            question == 7
            races == []
            professions == []
        }
    }

    def "Should get backstory questions"() {
        given: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/backstory/questions?ids=all", "/responses/story/backstory_questions.json", lang)

        when: "Requesting backstory questions"
        def questions = backstoryClient.getBackstoryQuestions(lang)

        then: "Retrieved questions match expected"
        verifyAll(questions.get(0)) {
            id == 7
            title == "My Personality"
            description == "Trouble may follow me, but I use my ______ to overcome it."
            answers == ["7-53", "7-54", "7-55"]
            order == 1
            races == []
            professions == []
        }
    }
}
