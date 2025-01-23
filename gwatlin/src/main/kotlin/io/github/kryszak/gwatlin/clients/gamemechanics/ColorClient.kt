package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.gamemechanics.model.color.DyeColor
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ColorClient : BaseHttpClient() {

    private val colorsEndpoint = "/colors"

    fun getAllColors(language: ApiLanguage?): List<DyeColor> {
        return getRequest("$colorsEndpoint?ids=all", language)
    }

    fun getColorIds(): List<Int> {
        return getRequest(colorsEndpoint)
    }

    fun getColor(id: Int, language: ApiLanguage?): DyeColor {
        return getRequest("$colorsEndpoint/$id", language)
    }

    fun getColors(ids: List<Int>, language: ApiLanguage?): List<DyeColor> {
        val params = ids.joinToString(",")
        return getRequest("$colorsEndpoint?ids=$params", language)
    }
}