package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class BackstoryClientSpec extends WiremockConfig {

    @Subject
    def backstoryClient = new GWBackstoryClient()

    def "Should get backstory answers"() {
        given: "External api is stubbed"
        stubResponse("/backstory/answers?ids=all&lang=en", "/responses/story/backstory_answers.json")

        when: "Requesting backstory answers"
        def answers = backstoryClient.getBackstoryAnswers("en")

        then: "Retrieved answers match expected"
        verifyAll(answers.get(0)) {
            id == "7-54"
            title == "Dignity"
            description == "I'm dignified even when up to my ears in mud. It's what makes people respect me. A serious, thoughtful demeanor is the route to success."
            journal == "Though trouble may follow me, I overcome it with dignity."
            question == 7
            races == null
            professions == null
        }
    }

    def "Should get backstory questions"() {
        given: "External api is stubbed"
        stubResponse("/backstory/questions?ids=all&lang=en", "/responses/story/backstory_questions.json")

        when: "Requesting backstory questions"
        def questions = backstoryClient.getBackstoryQuestions("en")

        then: "Retrieved questions match expected"
        verifyAll(questions.get(0)) {
            id == 7
            title == "My Personality"
            description == "Trouble may follow me, but I use my ______ to overcome it."
            answers == ["7-53", "7-54", "7-55"]
            order == 1
            races == null
            professions == null
        }
    }
}
