package io.github.kryszak.gwatlin.api.story

import io.github.kryszak.gwatlin.api.story.model.BackstoryAnswer
import io.github.kryszak.gwatlin.api.story.model.BackstoryQuestion
import io.github.kryszak.gwatlin.clients.backstory.BackstoryClient

/**
 * Client for backstory endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/characters/:id/backstory)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWBackstoryClient {

    private val backstoryClient: BackstoryClient = BackstoryClient()

    /**
     * Returns information about the Biography answers that are in the game
     */
    @JvmOverloads
    fun getBackstoryAnswers(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<BackstoryAnswer> {
        return backstoryClient.getBackstoryAnswers(language)
    }

    /**
     * Returns information about the Biography questions that are in the game
     */
    @JvmOverloads
    fun getBackstoryQuestions(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<BackstoryQuestion> {
        return backstoryClient.getBackstoryQuestions(language)
    }
}
