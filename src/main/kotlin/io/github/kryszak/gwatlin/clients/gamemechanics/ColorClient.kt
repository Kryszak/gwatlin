package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.color.DyeColor
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ColorClient : BaseHttpClient() {

    private val colorsEndpoint = "/colors"

    fun getAllColors(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<DyeColor> {
        return getRequest("$colorsEndpoint?ids=all", language)
    }
}