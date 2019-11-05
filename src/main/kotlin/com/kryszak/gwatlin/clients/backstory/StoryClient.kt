package com.kryszak.gwatlin.clients.backstory

import com.kryszak.gwatlin.api.story.model.Story
import com.kryszak.gwatlin.api.story.model.StorySeason
import com.kryszak.gwatlin.http.BaseHttpClient

internal class StoryClient : BaseHttpClient() {

    private val storyEndpoint = "stories"

    fun getStories(language: String): List<Story> {
        return getRequest("$storyEndpoint?ids=all&lang=$language")
    }

    fun getStorySeasons(language: String): List<StorySeason> {
        return getRequest("$storyEndpoint/seasons?ids=all&lang=$language")
    }
}
