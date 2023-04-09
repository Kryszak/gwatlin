package com.kryszak.gwatlin.clients.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.mount.skin.MountSkin
import com.kryszak.gwatlin.api.gamemechanics.model.mount.type.MountType
import com.kryszak.gwatlin.http.BaseHttpClient

internal class MountsClient : BaseHttpClient() {

    private val baseEndpoint: String = "mounts"

    private val skinsEndpoint: String = "$baseEndpoint/skins"

    private val typesEndpoint: String = "$baseEndpoint/types"

    fun getMountSkinsIds(): List<Int> {
        return getRequest(skinsEndpoint)
    }

    fun getMountSkins(ids: List<Int>, language: String): List<MountSkin> {
        val params = ids.joinToString(",")
        return getRequest("$skinsEndpoint?ids=$params", language)
    }

    fun getAllMountSkins(language: String): List<MountSkin> {
        return getRequest("$skinsEndpoint?ids=all", language)
    }

    fun getMountTypesIds(): List<String> {
        return getRequest(typesEndpoint)
    }

    fun getMountTypes(ids: List<String>, language: String): List<MountType> {
        val params = ids.joinToString(",")
        return getRequest("$typesEndpoint?ids=$params", language)
    }

    fun getAllMountTypes(language: String): List<MountType> {
        return getRequest("$typesEndpoint?ids=all", language)
    }
}
