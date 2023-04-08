package com.kryszak.gwatlin.clients.miscellaneous

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

    fun getQuaggans(): List<Icon> {
        return getRequest("$quaggansEndpoint?ids=all")
    }

    fun getColors(language: String): List<DyeColor> {
        return getRequest("$colorsEndpoint?ids=all", language)
    }

    fun getCurrencies(language: String): List<Currency> {
        return getRequest("$currenciesEndpoint?ids=all", language)
    }

    fun getDungeons(language: String): List<Dungeon> {
        return getRequest("$dungeonsEndpoint?ids=all", language)
    }

    fun getMinis(language: String): List<Mini> {
        return getRequest("$minisEndpoint?ids=all&", language)
    }

    fun getNovelties(language: String): List<Novelty> {
        return getRequest("$noveltiesEndpoint?ids=all", language)
    }

    fun getRaids(language: String): List<Raid> {
        return getRequest("$raidsEndpoint?ids=all", language)
    }

    fun getTitles(language: String): List<Title> {
        return getRequest("$titlesEndpoint?ids=all", language)
    }

    fun getWorlds(language: String): List<World> {
        return getRequest("$worldsEndpoint?ids=all", language)
    }
}
