package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.mount.MountSkin
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MountsClient : BaseHttpClient() {

    private val baseEndpoint: String = "mounts"

    private val skinsEndpoint: String = "$baseEndpoint/skins"

    private val typesEndpoint: String = "types"

    fun getMountSkinsIds(): List<Int> {
        return getRequest("$baseUrl/$skinsEndpoint")
    }

    fun getMountSkins(ids: List<Int>, language: String): List<MountSkin> {
        val params = ids.joinToString(",")
        return getRequest("$baseUrl/$skinsEndpoint?ids=$params&lang=$language")
    }

    fun getAllMountSkins(language: String): List<MountSkin> {
        return getRequest("$baseUrl/$skinsEndpoint?ids=all&lang=$language")
    }
}