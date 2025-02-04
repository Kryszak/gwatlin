package io.github.kryszak.gwatlin.clients.guild

import io.github.kryszak.gwatlin.api.guild.model.Guild
import io.github.kryszak.gwatlin.api.guild.model.emblem.Layer
import io.github.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import io.github.kryszak.gwatlin.api.guild.model.upgrade.GuildUpgrade
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class GuildClient : BaseHttpClient() {

    private val guildEndpoint = "/guild"

    private val emblemEndpoint = "/emblem"

    private val backgroundEndpoint = "$emblemEndpoint/backgrounds"

    private val foregroundEndpoint = "$emblemEndpoint/foregrounds"

    private val permissionEndpoint = "$guildEndpoint/permissions"

    private val upgradesEndpoint = "$guildEndpoint/upgrades"

    fun getGuild(id: String): Guild {
        return getRequest("$guildEndpoint/$id")
    }

    fun getBackgroundIds(): List<Int> {
        return getRequest(backgroundEndpoint)
    }

    fun getBackgrounds(ids: List<Int>): List<Layer> {
        val params = ids.joinToString(",")
        return getRequest("$backgroundEndpoint?ids=$params")
    }

    fun getForegroundIds(): List<Int> {
        return getRequest(foregroundEndpoint)
    }

    fun getForegrounds(ids: List<Int>): List<Layer> {
        val params = ids.joinToString(",")
        return getRequest("$foregroundEndpoint?ids=$params")
    }

    fun getGuildPermissionIds(): List<String> {
        return getRequest(permissionEndpoint)
    }

    fun getGuildPermissions(
        ids: List<String>,
        language: io.github.kryszak.gwatlin.api.ApiLanguage?
    ): List<GuildPermission> {
        val params = ids.joinToString(",")
        return getRequest("$permissionEndpoint?ids=$params", language)
    }

    fun findGuildId(name: String): String {
        val nameList: List<String> = getRequest("$guildEndpoint/search?name=${encodeParam(name)}")
        return when (nameList.isNotEmpty()) {
            true -> nameList[0]
            false -> ""
        }
    }

    fun getGuildUpgradesIds(): List<Int> {
        return getRequest(upgradesEndpoint)
    }

    fun getGuildUpgrades(ids: List<Int>, language: io.github.kryszak.gwatlin.api.ApiLanguage?): List<GuildUpgrade> {
        val params = ids.joinToString(",")
        return getRequest("$upgradesEndpoint?ids=$params", language)
    }
}
