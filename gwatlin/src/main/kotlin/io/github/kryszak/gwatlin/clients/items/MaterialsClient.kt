package io.github.kryszak.gwatlin.clients.items

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.items.model.material.Material
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MaterialsClient : BaseHttpClient() {

    private val materialsEndpoint = "/materials"

    fun getMaterialIds(): List<Int> {
        return getRequest(materialsEndpoint)
    }

    fun getMaterials(ids: List<Int>, language: ApiLanguage?): List<Material> {
        val params = ids.joinToString(",")
        return getRequest(materialsEndpoint, listOf("ids" to params), language)
    }
}
