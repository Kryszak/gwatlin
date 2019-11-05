package com.kryszak.gwatlin.clients.backstory

import com.kryszak.gwatlin.api.story.model.BackstoryAnswer
import com.kryszak.gwatlin.api.story.model.BackstoryQuestion
import com.kryszak.gwatlin.http.BaseHttpClient

internal class BackstoryClient : BaseHttpClient() {

    private val backstoryEndpoint = "backstory"

    fun getBackstoryAnswers(language: String): List<BackstoryAnswer> {
        return getRequest("$backstoryEndpoint/answers?ids=all&lang=$language")
    }

    fun getBackstoryQuestions(language: String): List<BackstoryQuestion> {
        return getRequest("$backstoryEndpoint/questions?ids=all&lang=$language")
    }
}
