package io.github.kryszak.gwatlin.clients.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.legend.LegendaryArmoryItem
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class LegendaryArmoryClient : BaseHttpClient() {

    private val endpoint = "/legendaryarmory"

    fun getLegendaryArmoryIds(): List<Long> {
        return getRequest(endpoint)
    }

    fun getLegendaryArmoryItem(id: Long): LegendaryArmoryItem {
        return getRequest("$endpoint/$id")
    }

    fun getLegendaryArmoryItems(ids: List<Long>): List<LegendaryArmoryItem> {
        val params = ids.joinToString(",")
        return getRequest("$endpoint?ids=$params")
    }

    fun getAllLegendaryArmoryItems(): List<LegendaryArmoryItem> {
        return getRequest("$endpoint?ids=all")
    }
}