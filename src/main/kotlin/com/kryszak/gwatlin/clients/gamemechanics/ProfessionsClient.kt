package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import com.kryszak.gwatlin.http.BaseHttpClient

internal class ProfessionsClient : BaseHttpClient() {

    private val professionsEndpoint = "professions"

    fun getProfessionIds(): List<String> {
        return getRequest(professionsEndpoint)
    }

    fun getProfessions(ids: List<String>, language: ApiLanguage?): List<Profession> {
        val params = ids.joinToString(",")
        return getRequest("$professionsEndpoint?ids=$params", language)
    }

    fun getAllProfessions(language: ApiLanguage?): List<Profession> {
        return getRequest("$professionsEndpoint?ids=all", language)
    }
}
