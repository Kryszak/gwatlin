package io.github.kryszak.gwatlin.clients.miscellaneous

import io.github.kryszak.gwatlin.api.miscellaneous.model.*
import io.github.kryszak.gwatlin.api.miscellaneous.model.color.DyeColor
import io.github.kryszak.gwatlin.api.miscellaneous.model.dungeon.Dungeon
import io.github.kryszak.gwatlin.api.miscellaneous.model.raid.Raid
import io.github.kryszak.gwatlin.http.BaseHttpClient

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

    fun getColors(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<DyeColor> {
        return getRequest("$colorsEndpoint?ids=all", language)
    }

    fun getCurrencies(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Currency> {
        return getRequest("$currenciesEndpoint?ids=all", language)
    }

    fun getDungeons(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Dungeon> {
        return getRequest("$dungeonsEndpoint?ids=all", language)
    }

    fun getMinis(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Mini> {
        return getRequest("$minisEndpoint?ids=all", language)
    }

    fun getNovelties(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Novelty> {
        return getRequest("$noveltiesEndpoint?ids=all", language)
    }

    fun getRaids(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Raid> {
        return getRequest("$raidsEndpoint?ids=all", language)
    }

    fun getTitles(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<Title> {
        return getRequest("$titlesEndpoint?ids=all", language)
    }

    fun getWorlds(language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<World> {
        return getRequest("$worldsEndpoint?ids=all", language)
    }
}
