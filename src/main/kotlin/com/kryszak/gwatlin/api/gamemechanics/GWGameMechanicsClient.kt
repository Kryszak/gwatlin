package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
import com.kryszak.gwatlin.api.gamemechanics.model.mount.MountSkin
import com.kryszak.gwatlin.clients.gamemechanics.MasteriesClient
import com.kryszak.gwatlin.clients.gamemechanics.MountsClient

/**
 * Client for game mechanics endpoints
 * @see com.kryszak.gwatlin.api.model.achievement.exception.ApiRequestException for errors
 */
class GWGameMechanicsClient {

    private val masteriesClient: MasteriesClient = MasteriesClient()

    private val mountsClient: MountsClient = MountsClient()

    /**
     * Retrieves list of all mastery ids
     * @return mastery ids list
     */
    fun getMasteriesIds(): List<Int> {
        return masteriesClient.getMasteriesIds()
    }

    /**
     * Retrieves specific mastery
     * @param id of mastery
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    fun getMastery(id: Int, language: String = "en"): Mastery {
        return masteriesClient.getMastery(id, language)
    }

    /**
     * Retrieves list of masteries
     * @param ids list of mastery ids
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mastery.Mastery
     */
    fun getMasteries(ids: List<Int>, language: String = "en"): List<Mastery> {
        return masteriesClient.getMasteries(ids, language)
    }

    /**
     * Retrieves all masteries
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.Mastery
     */
    fun getAllMasteries(language: String = "en"): List<Mastery> {
        return masteriesClient.getAllMasteries(language)
    }

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
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mount.MountSkin
     */
    fun getMountSkins(ids: List<Int>, language: String = "en"): List<MountSkin> {
        return mountsClient.getMountSkins(ids, language)
    }

    /**
     * Retrieves all mount skins
     * @param language of returned text (default=en)
     * @see com.kryszak.gwatlin.api.gamemechanics.model.mount.MountSkin
     */
    fun getAllMountSkins(language: String = "en"): List<MountSkin> {
        return mountsClient.getAllMountSkins(language)
    }
}