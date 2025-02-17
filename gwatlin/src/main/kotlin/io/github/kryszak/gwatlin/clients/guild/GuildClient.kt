package io.github.kryszak.gwatlin.clients.guild

import io.github.kryszak.gwatlin.api.ApiLanguage
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
        return getRequest(backgroundEndpoint, listOf("ids" to params))
    }

    fun getForegroundIds(): List<Int> {
        return getRequest(foregroundEndpoint)
    }

    fun getForegrounds(ids: List<Int>): List<Layer> {
        val params = ids.joinToString(",")
        return getRequest(foregroundEndpoint, listOf("ids" to params))
    }

    fun getGuildPermissionIds(): List<String> {
        return getRequest(permissionEndpoint)
    }

    fun getGuildPermissions(ids: List<String>, language: ApiLanguage?): List<GuildPermission> {
        val params = ids.joinToString(",")
        return getRequest(permissionEndpoint, listOf("ids" to params), language)
    }

    fun findGuildId(name: String): String {
        val nameList: List<String> =
            getRequest("$guildEndpoint/search", listOf("name" to name))
        return when (nameList.isNotEmpty()) {
            true -> nameList[0]
            false -> ""
        }
    }

    fun getGuildUpgradesIds(): List<Int> {
        return getRequest(upgradesEndpoint)
    }

    fun getGuildUpgrades(ids: List<Int>, language: ApiLanguage?): List<GuildUpgrade> {
        val params = ids.joinToString(",")
        return getRequest(upgradesEndpoint, listOf("ids" to params), language)
    }
}
