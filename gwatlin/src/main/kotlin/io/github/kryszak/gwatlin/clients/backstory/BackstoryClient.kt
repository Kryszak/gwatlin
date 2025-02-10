package io.github.kryszak.gwatlin.clients.backstory

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.story.model.BackstoryAnswer
import io.github.kryszak.gwatlin.api.story.model.BackstoryQuestion
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class BackstoryClient : BaseHttpClient() {

    private val backstoryEndpoint = "/backstory"

    fun getBackstoryAnswers(language: ApiLanguage?): List<BackstoryAnswer> {
        return getRequest("$backstoryEndpoint/answers", listOf("ids" to "all"), language)
    }

    fun getBackstoryQuestions(language: ApiLanguage?): List<BackstoryQuestion> {
        return getRequest("$backstoryEndpoint/questions", listOf("ids" to "all"), language)
    }
}
