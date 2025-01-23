package io.github.kryszak.gwatlin.clients.backstory

import io.github.kryszak.gwatlin.api.story.model.BackstoryAnswer
import io.github.kryszak.gwatlin.api.story.model.BackstoryQuestion
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class BackstoryClient : BaseHttpClient() {

    private val backstoryEndpoint = "/backstory"

    fun getBackstoryAnswers(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<BackstoryAnswer> {
        return getRequest("$backstoryEndpoint/answers?ids=all", language)
    }

    fun getBackstoryQuestions(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<BackstoryQuestion> {
        return getRequest("$backstoryEndpoint/questions?ids=all", language)
    }
}
