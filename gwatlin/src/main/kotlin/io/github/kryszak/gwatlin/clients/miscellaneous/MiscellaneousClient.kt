package io.github.kryszak.gwatlin.clients.miscellaneous

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.miscellaneous.model.BuildId
import io.github.kryszak.gwatlin.api.miscellaneous.model.Icon
import io.github.kryszak.gwatlin.api.miscellaneous.model.Quaggan
import io.github.kryszak.gwatlin.api.miscellaneous.model.World
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MiscellaneousClient : BaseHttpClient() {

    private val buildEndpoint = "/build"

    private val filesEndpoint = "/files"

    private val quaggansEndpoint = "/quaggans"

    private val worldsEndpoint = "/worlds"

    fun getBuildId(): BuildId {
        return getRequest(buildEndpoint)
    }

    fun getIcons(): List<Icon> {
        return getRequest(filesEndpoint, listOf("ids" to "all"))
    }

    fun getQuaggans(): List<Quaggan> {
        return getRequest(quaggansEndpoint, listOf("ids" to "all"))
    }

    fun getWorlds(language: ApiLanguage?): List<World> {
        return getRequest(worldsEndpoint, listOf("ids" to "all"), language)
    }
}
