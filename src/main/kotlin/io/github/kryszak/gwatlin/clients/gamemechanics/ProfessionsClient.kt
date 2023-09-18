package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.profession.Profession
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ProfessionsClient : BaseHttpClient() {

    private val professionsEndpoint = "professions"

    fun getProfessionIds(): List<String> {
        return getRequest(professionsEndpoint)
    }

    fun getProfessions(ids: List<String>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Profession> {
        val params = ids.joinToString(",")
        return getRequest("$professionsEndpoint?ids=$params", language)
    }

    fun getAllProfessions(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Profession> {
        return getRequest("$professionsEndpoint?ids=all", language)
    }
}
