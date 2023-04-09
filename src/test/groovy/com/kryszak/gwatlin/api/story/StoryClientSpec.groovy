package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockTest
import spock.lang.Subject

class StoryClientSpec extends WiremockTest {

    @Subject
    def storyClient = new GWStoryClient()

    def "Should get stories"() {
        given: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/stories?ids=all", "/responses/story/stories.json", lang)

        when: "Requesting stories"
        def stories = storyClient.getStories(lang)

        then: "Retrieved stories match expected"
        verifyAll(stories.get(0)) {
            id == 1
            season == "215AAA0F-CDAC-4F93-86DA-C155A99B5784"
            name == "My Story"
            description == ""
            timeline == "1325 AE"
            level == 1
            races == ["Charr"]
            order == 0
            professions == null
            verifyAll(chapters.get(0)) {
                name == "1. Getting the Band Back Together"
            }
        }
    }

    def "Should get story seasons"() {
        given: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/stories/seasons?ids=all", "/responses/story/story_seasons.json", lang)

        when: "Requesting story seasons"
        def seasons = storyClient.getStorySeasons(lang)

        then: "Retrieved seasons matches expected"
        verifyAll(seasons.get(0)) {
            id == "09766A86-D88D-4DF2-9385-259E9A8CA583"
            name == "Living World Season 3"
            order == 5
            stories == [63, 65, 66, 56, 46, 64]
        }
    }
}
