package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.specialization.Specialization
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class SpecializationClient : BaseHttpClient() {

    private val specializationEndpoint: String = "/specializations"

    fun getSpecializationIds(): List<Int> {
        return getRequest(specializationEndpoint)
    }

    fun getSpecialization(id: Int, language: ApiLanguage?): Specialization {
        return getRequest("$specializationEndpoint/$id", listOf(), language)
    }

    fun getSpecializations(ids: List<Int>, language: ApiLanguage?): List<Specialization> {
        val params = ids.joinToString(",")
        return getRequest("$specializationEndpoint?ids=$params", listOf(), language)
    }

    fun getPagedSpecializations(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Specialization>> {
        return getPagedRequest("$specializationEndpoint?${pageRequest.toQueryParams()}", listOf(), language)
    }
}