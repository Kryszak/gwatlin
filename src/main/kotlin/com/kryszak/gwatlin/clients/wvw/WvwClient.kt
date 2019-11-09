package com.kryszak.gwatlin.clients.wvw

import com.kryszak.gwatlin.api.wvw.model.ability.WvwAbility
import com.kryszak.gwatlin.http.BaseHttpClient

internal class WvwClient : BaseHttpClient() {

    private val wvwEndpoint = "wvw"

    fun getAbilityIds(): List<Int> {
        return getRequest("$wvwEndpoint/abilities")
    }

    fun getAbilities(ids: List<Int>, language: String): List<WvwAbility> {
        val params = ids.joinToString(",")
        return getRequest("$wvwEndpoint/abilities?ids=$params&lang=$language")
    }
}