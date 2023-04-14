package com.kryszak.gwatlin.api.story

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.story.model.BackstoryAnswer
import com.kryszak.gwatlin.api.story.model.BackstoryQuestion
import com.kryszak.gwatlin.clients.backstory.BackstoryClient

/**
 * Client for backstory endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWBackstoryClient {

    private val backstoryClient: BackstoryClient = BackstoryClient()

    /**
     * Returns information about the Biography answers that are in the game
     */
    @JvmOverloads
    fun getBackstoryAnswers(language: ApiLanguage? = null): List<BackstoryAnswer> {
        return backstoryClient.getBackstoryAnswers(language)
    }

    /**
     * Returns information about the Biography questions that are in the game
     */
    @JvmOverloads
    fun getBackstoryQuestions(language: ApiLanguage? = null): List<BackstoryQuestion> {
        return backstoryClient.getBackstoryQuestions(language)
    }
}
