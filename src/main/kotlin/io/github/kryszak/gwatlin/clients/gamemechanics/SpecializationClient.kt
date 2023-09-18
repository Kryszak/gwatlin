package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SpecializationClient : BaseHttpClient() {

    private val specializationEndpoint: String = "specializations"

    fun getSpecializationIds(): List<Int> {
        return getRequest(specializationEndpoint)
    }

    fun getSpecialization(id: Int, language: io.github.kryszak.gwatlin.api.ApiLanguage?): Specialization {
        return getRequest("$specializationEndpoint/$id", language)
    }
}