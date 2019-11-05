package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.api.story.model.Story
import com.kryszak.gwatlin.api.story.model.StorySeason
import com.kryszak.gwatlin.clients.backstory.StoryClient

/**
 * Client for story endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWStoryClient {

    private val storyClient: StoryClient = StoryClient()

    /**
     * Returns information about the Story Journal stories; including the personal story and Living World
     */
    fun getStories(language: String = "en"): List<Story> {
        return storyClient.getStories(language)
    }

    /**
     * Returns information about the Story Journal story seasons; including the personal story and Living World
     */
    fun getStorySeasons(language: String = "en"): List<StorySeason> {
        return storyClient.getStorySeasons(language)
    }
}
