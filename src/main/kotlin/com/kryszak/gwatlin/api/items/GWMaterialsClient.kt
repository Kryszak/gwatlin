package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.material.Material
import com.kryszak.gwatlin.clients.items.MaterialsClient

/**
 * Client for materials endpoints
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMaterialsClient {

    private val materialsClient: MaterialsClient = MaterialsClient()

    /**
     * Retrieves all material ids
     */
    fun getMaterialIds(): List<Int> {
        return materialsClient.getMaterialIds()
    }

    /**
     * Retrieves specific materials
     * @param ids od materials
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.items.model.material.Material
     */
    fun getMaterials(ids: List<Int>, language: ApiLanguage? = null): List<Material> {
        return materialsClient.getMaterials(ids, language)
    }
}
