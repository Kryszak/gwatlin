package io.github.kryszak.gwatlin.clients.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.mount.skin.MountSkin
import io.github.kryszak.gwatlin.api.wardrobe.model.mount.type.MountType
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class MountsClient : BaseHttpClient() {

    private val baseEndpoint: String = "/mounts"

    private val skinsEndpoint: String = "$baseEndpoint/skins"

    private val typesEndpoint: String = "$baseEndpoint/types"

    fun getMountSkinsIds(): List<Int> {
        return getRequest(skinsEndpoint)
    }

    fun getMountSkins(ids: List<Int>, language: ApiLanguage?): List<MountSkin> {
        val params = ids.joinToString(",")
        return getRequest(skinsEndpoint, listOf("ids" to params), language)
    }

    fun getAllMountSkins(language: ApiLanguage?): List<MountSkin> {
        return getRequest(skinsEndpoint, listOf("ids" to "all"), language)
    }

    fun getPagedMountSkins(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<MountSkin>> {
        return getPagedRequest(skinsEndpoint, pageRequest.toQueryParams(), language)
    }

    fun getMountTypesIds(): List<String> {
        return getRequest(typesEndpoint)
    }

    fun getMountTypes(ids: List<String>, language: ApiLanguage?): List<MountType> {
        val params = ids.joinToString(",")
        return getRequest(typesEndpoint, listOf("ids" to params), language)
    }

    fun getAllMountTypes(language: ApiLanguage?): List<MountType> {
        return getRequest(typesEndpoint, listOf("ids" to "all"), language)
    }
}
