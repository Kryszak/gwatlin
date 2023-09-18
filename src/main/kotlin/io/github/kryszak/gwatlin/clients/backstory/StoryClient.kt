package io.github.kryszak.gwatlin.clients.backstory

import io.github.kryszak.gwatlin.api.story.model.Story
import io.github.kryszak.gwatlin.api.story.model.StorySeason
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class StoryClient : BaseHttpClient() {

    private val storyEndpoint = "stories"

    fun getStories(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Story> {
        return getRequest("$storyEndpoint?ids=all", language)
    }

    fun getStorySeasons(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<StorySeason> {
        return getRequest("$storyEndpoint/seasons?ids=all", language)
    }
}
