package io.github.kryszak.gwatlin.api.gamemechanics

import io.github.kryszak.gwatlin.api.gamemechanics.model.mount.skin.MountSkin
import io.github.kryszak.gwatlin.api.gamemechanics.model.mount.type.MountType
import io.github.kryszak.gwatlin.clients.gamemechanics.MountsClient

/**
 * Client for game mechanic - mounts endpoints
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mount.skin.MountSkin
     */
    @JvmOverloads
    fun getMountSkins(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountSkin> {
        return mountsClient.getMountSkins(ids, language)
    }

    /**
     * Retrieves all mount skins
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mount.skin.MountSkin
     */
    @JvmOverloads
    fun getAllMountSkins(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountSkin> {
        return mountsClient.getAllMountSkins(language)
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
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mount.type.MountType
     */
    @JvmOverloads
    fun getMountTypes(ids: List<String>, language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountType> {
        return mountsClient.getMountTypes(ids, language)
    }

    /**
     * Retrieves all mount types
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.gamemechanics.model.mount.type.MountType
     */
    @JvmOverloads
    fun getAllMountTypes(language: io.github.kryszak.gwatlin.api.ApiLanguage? = null): List<MountType> {
        return mountsClient.getAllMountTypes(language)
    }
}
