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
        return getRequest("$colorsEndpoint?ids=all&lang=$language")
    }

    fun getCurrencies(language: String): List<Currency> {
        return getRequest("currencies?ids=all&lang=$language")
    }

    fun getDungeons(language: String): List<Dungeon> {
        return getRequest("dungeons?ids=all&lang=$language")
    }

    fun getMinis(language: String): List<Mini> {
        return getRequest("minis?ids=all&lang=$language")
    }

    fun getNovelties(language: String): List<Novelty> {
        return getRequest("novelties?ids=all&lang=$language")
    }

    fun getRaids(language: String): List<Raid> {
        return getRequest("raids?ids=all&lang=$language")
    }

    fun getTitles(language: String): List<Title> {
        return getRequest("titles?ids=all&lang=$language")
    }

    fun getWorlds(language: String): List<World> {
        return getRequest("worlds?ids=all&lang=$language")
    }
}
