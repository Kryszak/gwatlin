package com.kryszak.gwatlin.clients.backstory

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.story.model.BackstoryAnswer
import com.kryszak.gwatlin.api.story.model.BackstoryQuestion
import com.kryszak.gwatlin.http.BaseHttpClient

internal class BackstoryClient : BaseHttpClient() {

    private val backstoryEndpoint = "backstory"

    fun getBackstoryAnswers(language: ApiLanguage?): List<BackstoryAnswer> {
        return getRequest("$backstoryEndpoint/answers?ids=all", language)
    }

    fun getBackstoryQuestions(language: ApiLanguage?): List<BackstoryQuestion> {
        return getRequest("$backstoryEndpoint/questions?ids=all", language)
    }
}
