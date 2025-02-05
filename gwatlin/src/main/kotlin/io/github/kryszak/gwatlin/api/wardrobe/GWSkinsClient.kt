package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.skins.Skin
import io.github.kryszak.gwatlin.clients.wardrobe.SkinsClient

/**
 * Client for skins endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/skins)
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWSkinsClient {

    private val skinsClient: SkinsClient = SkinsClient()

    /**
     * Retrieves all skin ids
     */
    fun getSkinIds(): List<Int> {
        return skinsClient.getSkinIds()
    }

    /**
     * Retrieves specific skins
     * @param ids of skins
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.skins.Skin
     */
    fun getSkins(ids: List<Int>, language: ApiLanguage? = null): List<Skin> {
        return skinsClient.getSkins(ids, language)
    }

    /**
     * Retrieves paged skins
     * @param language of returned text (default=en)
     */
    fun getPagedSkins(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Skin>> {
        return skinsClient.getPagedSkins(pageRequest, language)
    }
}