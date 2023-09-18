package io.github.kryszak.gwatlin.api.guild

import io.github.kryszak.gwatlin.api.guild.model.Guild
import io.github.kryszak.gwatlin.api.guild.model.emblem.Layer
import io.github.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import io.github.kryszak.gwatlin.api.guild.model.upgrade.GuildUpgrade
import io.github.kryszak.gwatlin.clients.guild.GuildClient

/**
 * Client for guild unauthenticated endpoints
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWGuildClient {

    private val guildClient: GuildClient = GuildClient()

    /**
     * Retrieves guild
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.Guild
     */
    fun getGuild(id: String): Guild {
        return guildClient.getGuild(id)
    }

    /**
     * Retrieves list of all background ids
     */
    fun getBackgroundIds(): List<Int> {
        return guildClient.getBackgroundIds()
    }

    /**
     * Retrieves specific backgrounds
     * @param ids of backgrounds
     * @see io.github.kryszak.gwatlin.api.guild.model.emblem.Layer
     */
    fun getBackgrounds(ids: List<Int>): List<Layer> {
        return guildClient.getBackgrounds(ids)
    }

    /**
     * Retrieves list of all foreground ids
     */
    fun getForegroundIds(): List<Int> {
        return guildClient.getForegroundIds()
    }

    /**
     * Retrieves specific foregrounds
     * @param ids of foregrounds
     * @see io.github.kryszak.gwatlin.api.guild.model.emblem.Layer
     */
    fun getForegrounds(ids: List<Int>): List<Layer> {
        return guildClient.getForegrounds(ids)
    }

    /**
     * Retrieves list of all guild permission ids
     */
    fun getGuildPermissionIds(): List<String> {
        return guildClient.getGuildPermissionIds()
    }

    /**
     * Retrieves list of guild permissions
     * @param ids of permissions
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.guild.model.permission.GuildPermission
     */
    @JvmOverloads
    fun getGuildPermissions(
        ids: List<String>,
        language: io.github.kryszak.gwatlin.api.ApiLanguage? = null
    ): List<GuildPermission> {
        return guildClient.getGuildPermissions(ids, language)
    }

    /**
     * Searches for guild id
     * @param name of the guild
     */
    fun findGuildId(name: String): String {
        return guildClient.findGuildId(name)
    }

    /**
     * Retrieves list of all guild upgrade ids
     */
    fun getGuildUpgradeIds(): List<Int> {
        return guildClient.getGuildUpgradesIds()
    }

    /**
     * Retrieves specific guild upgrades
     * @param ids od upgrades
     * @param language of returned text (default=en)
     * @see io.github.kryszak.gwatlin.api.guild.model.upgrade.GuildUpgrade
     */
    @JvmOverloads
    fun getGuildUpgrades(
        ids: List<Int>,
        language: io.github.kryszak.gwatlin.api.ApiLanguage? = null
    ): List<GuildUpgrade> {
        return guildClient.getGuildUpgrades(ids, language)
    }
}
