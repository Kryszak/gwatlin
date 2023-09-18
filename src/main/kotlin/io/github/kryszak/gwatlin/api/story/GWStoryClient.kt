package io.github.kryszak.gwatlin.api.story

import io.github.kryszak.gwatlin.api.story.model.Story
import io.github.kryszak.gwatlin.api.story.model.StorySeason
import io.github.kryszak.gwatlin.clients.backstory.StoryClient

/**
 * Client for story endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWStoryClient {

    private val storyClient: StoryClient = StoryClient()

    /**
     * Returns information about the Story Journal stories; including the personal story and Living World
     */
    @JvmOverloads
    fun getStories(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Story> {
        return storyClient.getStories(language)
    }

    /**
     * Returns information about the Story Journal story seasons; including the personal story and Living World
     */
    @JvmOverloads
    fun getStorySeasons(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<StorySeason> {
        return storyClient.getStorySeasons(language)
    }
}
