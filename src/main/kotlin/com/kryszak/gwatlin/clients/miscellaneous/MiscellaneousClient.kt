package com.kryszak.gwatlin.clients.miscellaneous

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.miscellaneous.model.*
import com.kryszak.gwatlin.api.miscellaneous.model.color.DyeColor
import com.kryszak.gwatlin.api.miscellaneous.model.dungeon.Dungeon
import com.kryszak.gwatlin.api.miscellaneous.model.raid.Raid
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MiscellaneousClient : BaseHttpClient() {

    private val buildEndpoint = "build"

    private val filesEndpoint = "files"

    private val quaggansEndpoint = "quaggans"

    private val colorsEndpoint = "colors"

    private val currenciesEndpoint = "currencies"

    private val dungeonsEndpoint = "dungeons"

    private val minisEndpoint = "minis"

    private val noveltiesEndpoint = "novelties"

    private val raidsEndpoint = "raids"

    private val titlesEndpoint = "titles"

    private val worldsEndpoint = "worlds"

    fun getBuildId(): BuildId {
        return getRequest(buildEndpoint)
    }

    fun getIcons(): List<Icon> {
        return getRequest("$filesEndpoint?ids=all")
    }

    fun getQuaggans(): List<Quaggan> {
        return getRequest("$quaggansEndpoint?ids=all")
    }

    fun getColors(language: ApiLanguage?): List<DyeColor> {
        return getRequest("$colorsEndpoint?ids=all", language)
    }

    fun getCurrencies(language: ApiLanguage?): List<Currency> {
        return getRequest("$currenciesEndpoint?ids=all", language)
    }

    fun getDungeons(language: ApiLanguage?): List<Dungeon> {
        return getRequest("$dungeonsEndpoint?ids=all", language)
    }

    fun getMinis(language: ApiLanguage?): List<Mini> {
        return getRequest("$minisEndpoint?ids=all", language)
    }

    fun getNovelties(language: ApiLanguage?): List<Novelty> {
        return getRequest("$noveltiesEndpoint?ids=all", language)
    }

    fun getRaids(language: ApiLanguage?): List<Raid> {
        return getRequest("$raidsEndpoint?ids=all", language)
    }

    fun getTitles(language: ApiLanguage?): List<Title> {
        return getRequest("$titlesEndpoint?ids=all", language)
    }

    fun getWorlds(language: ApiLanguage?): List<World> {
        return getRequest("$worldsEndpoint?ids=all", language)
    }
}
