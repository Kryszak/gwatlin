package com.kryszak.gwatlin.clients.items

import com.kryszak.gwatlin.api.items.model.material.Material
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MaterialsClient : BaseHttpClient() {

    private val materialsEndpoint = "materials"

    fun getMaterialIds(): List<Int> {
        return getRequest(materialsEndpoint)
    }

    fun getMaterials(ids: List<Int>, language: String): List<Material> {
        val params = ids.joinToString(",")
        return getRequest("$materialsEndpoint?ids=$params", language)
    }
}
