package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.color.DyeColor
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ColorClient : BaseHttpClient() {

    private val colorsEndpoint = "/colors"

    fun getAllColors(language: ApiLanguage?): List<DyeColor> {
        return getRequest(colorsEndpoint, listOf("ids" to "all"), language)
    }

    fun getColorIds(): List<Int> {
        return getRequest(colorsEndpoint)
    }

    fun getColor(id: Int, language: ApiLanguage?): DyeColor {
        return getRequest("$colorsEndpoint/$id", listOf(), language)
    }

    fun getColors(ids: List<Int>, language: ApiLanguage?): List<DyeColor> {
        val params = ids.joinToString(",")
        return getRequest(colorsEndpoint, listOf("ids" to params), language)
    }

    fun getPagedColors(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<DyeColor>> {
        return getPagedRequest(colorsEndpoint, pageRequest.toQueryParams(), language)
    }
}