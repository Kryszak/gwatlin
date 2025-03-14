package io.github.kryszak.gwatlin.clients.backstory

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.story.model.Story
import io.github.kryszak.gwatlin.api.story.model.StorySeason
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class StoryClient : BaseHttpClient() {

    private val storyEndpoint = "/stories"

    fun getStories(language: ApiLanguage?): List<Story> {
        return getRequest(storyEndpoint, listOf("ids" to "all"), language)
    }

    fun getStorySeasons(language: ApiLanguage?): List<StorySeason> {
        return getRequest("$storyEndpoint/seasons", listOf("ids" to "all"), language)
    }
}
