package io.github.kryszak.gwatlin.api.wardrobe

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.api.wardrobe.model.mount.skin.MountSkin
import io.github.kryszak.gwatlin.api.wardrobe.model.mount.type.MountType
import io.github.kryszak.gwatlin.clients.wardrobe.MountsClient

/**
 * Client for wardrobe - mounts endpoints. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/mounts).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWMountsClient {

    private val mountsClient: MountsClient = MountsClient()


    /**
     * Retrieves list of all mount skin ids
     * @return list of mount skin ids
     */
    fun getMountSkinsIds(): List<Int> {
        return mountsClient.getMountSkinsIds()
    }

    /**
     * Retrieves mount skins by ids
     * @param ids mount skin ids
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.mount.skin.MountSkin
     */
    @JvmOverloads
    fun getMountSkins(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountSkin> {
        return mountsClient.getMountSkins(ids, language)
    }

    /**
     * Retrieves all mount skins
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.mount.skin.MountSkin
     */
    @JvmOverloads
    fun getAllMountSkins(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountSkin> {
        return mountsClient.getAllMountSkins(language)
    }

    /**
     * Retrieves paged mount skins
     * @param language of returned text (default=en)
     */
    @JvmOverloads
    fun getPagedMountSkins(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<MountSkin>> {
        return mountsClient.getPagedMountSkins(pageRequest, language)
    }

    /**
     * Retrieves all mount types ids
     * @return list of mount type ids
     */
    fun getMountTypesIds(): List<String> {
        return mountsClient.getMountTypesIds()
    }

    /**
     * Retrieves mount types by given ids
     * @param ids of mount types
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.mount.type.MountType
     */
    @JvmOverloads
    fun getMountTypes(ids: List<String>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountType> {
        return mountsClient.getMountTypes(ids, language)
    }

    /**
     * Retrieves all mount types
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.wardrobe.model.mount.type.MountType
     */
    @JvmOverloads
    fun getAllMountTypes(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountType> {
        return mountsClient.getAllMountTypes(language)
    }
}
