package com.kryszak.gwatlin.clients.backstory

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.story.model.Story
import com.kryszak.gwatlin.api.story.model.StorySeason
import com.kryszak.gwatlin.http.BaseHttpClient

internal class StoryClient : BaseHttpClient() {

    private val storyEndpoint = "stories"

    fun getStories(language: ApiLanguage?): List<Story> {
        return getRequest("$storyEndpoint?ids=all", language)
    }

    fun getStorySeasons(language: ApiLanguage?): List<StorySeason> {
        return getRequest("$storyEndpoint/seasons?ids=all", language)
    }
}
