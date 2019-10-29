package com.kryszak.gwatlin.clients.guild

import com.kryszak.gwatlin.api.guild.model.Guild
import com.kryszak.gwatlin.api.guild.model.emblem.Layer
import com.kryszak.gwatlin.api.guild.model.permission.GuildPermission
import com.kryszak.gwatlin.http.BaseHttpClient

internal class GuildClient : BaseHttpClient() {

    private val guildEndpoint = "guild"

    private val emblemEndpoint = "emblem"

    private val backgroundEndpoint = "$emblemEndpoint/backgrounds"

    private val foregroundEndpoint = "$emblemEndpoint/foregrounds"

    private val permissionEndpoint = "$guildEndpoint/permissions"

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

    fun getGuildPermissions(ids: List<String>, language: String): List<GuildPermission> {
        val params = ids.joinToString(",")
        return getRequest("$permissionEndpoint?ids=$params&lang=$language")
    }
}
