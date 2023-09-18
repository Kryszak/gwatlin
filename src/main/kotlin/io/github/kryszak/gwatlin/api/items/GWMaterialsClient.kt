package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.items.model.material.Material
import io.github.kryszak.gwatlin.clients.items.MaterialsClient

/**
 * Client for materials endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/materials)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
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
     * @see io.github.kryszak.gwatlin.api.items.model.material.Material
     */
    @JvmOverloads
    fun getMaterials(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<Material> {
        return materialsClient.getMaterials(ids, language)
    }
}
