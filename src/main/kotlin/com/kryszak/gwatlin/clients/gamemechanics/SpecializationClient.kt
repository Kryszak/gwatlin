package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import com.kryszak.gwatlin.http.BaseHttpClient

internal class SpecializationClient : BaseHttpClient() {

    private val specializationEndpoint: String = "specializations"

    fun getSpecializationIds(): List<Int> {
        return getRequest(specializationEndpoint)
    }

    fun getSpecialization(id: Int, language: ApiLanguage?): Specialization {
        return getRequest("$specializationEndpoint/$id", language)
    }
}